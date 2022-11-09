package com.example.trua_nay_an_gi.service.cart;

import com.example.trua_nay_an_gi.model.app_users.AppUser;
import com.example.trua_nay_an_gi.model.app_users.Merchant;
import com.example.trua_nay_an_gi.model.delivery.Delivery;
import com.example.trua_nay_an_gi.model.dto.cart.CartDetailDto;
import com.example.trua_nay_an_gi.model.dto.cart.CartDto;
import com.example.trua_nay_an_gi.model.dto.order.OrderDto;
import com.example.trua_nay_an_gi.model.product.*;
import com.example.trua_nay_an_gi.repository.ICartRepository;
import com.example.trua_nay_an_gi.service.account.product.IProductService;
import com.example.trua_nay_an_gi.service.app_users.IAppUserSevice;
import com.example.trua_nay_an_gi.service.cart_detail.ICartDetailService;
import com.example.trua_nay_an_gi.service.merchant.IMerchantService;
import com.example.trua_nay_an_gi.service.order.IOrderService;
import com.example.trua_nay_an_gi.service.order_detail.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    ICartRepository cartRepository;

    @Autowired
    ICartDetailService cartDetailService;

    @Autowired
    IOrderService orderService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductService productService;

    @Autowired
    IAppUserSevice appUserSevice;

    @Autowired
    IMerchantService merchantService;

    @Override
    public Iterable<Cart> findAll() {
        return cartRepository.findAll();
    }

    @Override
    public Optional<Cart> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void remove(Long id) {
        cartRepository.deleteById(id);
    }

    @Override
    public Optional<Cart> findCartByUserAndMerchant(AppUser appUser, Merchant merchant) {
        return cartRepository.findCartByUserAndMerchant(appUser,merchant);
    }

    @Override
    public Iterable<Cart> findAllCartByUser(AppUser appUser) {
        return cartRepository.findCartByUser(appUser);
    }

    @Override
    public Cart createCartWithUserAndMerchant(AppUser appUser, Merchant merchant) {
        Cart cart = new Cart();
        cart.setUser(appUser);
        cart.setMerchant(merchant);
        return cart;
    }

    @Override
    public CartDto getCartDtoByUserAndMerchant(AppUser appUser, Merchant merchant) {
        Optional<Cart> findCart = cartRepository.findCartByUserAndMerchant(appUser,merchant);
        Cart cart;
        if (findCart.isPresent()){
            cart=findCart.get();
        }else {
            cart = new Cart();
            cart.setUser(appUser);
            cart.setMerchant(merchant);
            cart = this.save(cart);
        }
        CartDto cartDto = makeCartDtoFromCart(cart);
        return cartDto;
    }

    private CartDto makeCartDtoFromCart(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(cart.getId());
        cart.setUser(cart.getUser());
        cartDto.setMerchant(cart.getMerchant());

        Iterable<CartDetail> cartDetails = cartDetailService.findAllByCart(cart);
        cartDetails.forEach(
                cartDetail -> {
                    CartDetailDto cartDetailDto = new CartDetailDto();
                    cartDetailDto.setProduct(cartDetail.getProduct());
                    cartDetailDto.setQuantity(cartDetail.getQuantity());
                    cartDto.addCartDetailDto(cartDetailDto);
                }
        );
        return cartDto;

    }

    @Override
    public List<CartDto> getAllCartDtoByUser(AppUser appUser) {
        Iterable<Cart> carts = findAllCartByUser(appUser);
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts){
            List<CartDetail> cartDetails = (List<CartDetail>) cartDetailService.findAllByCart(cart);
            if (cartDetails.size() == 0){
                cartRepository.deleteById(cart.getId());
                break;
            }
            CartDto cartDto = makeCartDtoFromCart(cart);
            cartDtos.add(cartDto);
        }
        return cartDtos;
    }

    @Override
    public CartDto addProductToCart(AppUser appUser, CartDetailDto cartDetailDto) {
        Product product = cartDetailDto.getProduct();
        Merchant merchant = product.getMerchant();
        int quantity = cartDetailDto.getQuantity();
        CartDto cartDto = getCartDtoByUserAndMerchant(appUser,merchant);
        Long cartId = cartDto.getId();
        Cart cart = findById(cartId).get();

        Optional<CartDetail> findCartDetail = cartDetailService.findByCartAndProduct(cart,product);
        CartDetail cartDetail;
        if (findCartDetail.isPresent()) {
            cartDetail = findCartDetail.get();
            cartDetail.setQuantity(cartDetail.getQuantity()+quantity);
        }else {
            cartDetail=new CartDetail();
            cartDetail.setProduct(product);
            cartDetail.setCart(cart);
            cartDetail.setQuantity(quantity);
        }
        cartDetailService.save(cartDetail);
        cartDto = getCartDtoByUserAndMerchant(appUser , merchant);
        return cartDto;
    }

    @Override
    public boolean changeProductQuantityInCart(Cart cart, Product product, int amount) {
        Optional<CartDetail> findCartDetail = cartDetailService.findByCartAndProduct(cart,product);
        CartDetail cartDetail;
        if (findCartDetail.isPresent()){
            cartDetail = findCartDetail.get();
            cartDetail.setQuantity(cartDetail.getQuantity() + amount);
        }else {
            if (amount < 0) return false;
            cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setQuantity(amount);
        }
        if (cartDetail.getQuantity()<1){
            cartDetailService.remove(cartDetail.getId());
        }else {
            cartDetailService.save(cartDetail);
        }
        return true;
    }

    public Order saveOrderToDB(AppUser appUser, OrderDto orderDto) {
        Order order = new Order();
        order.setAppUser(appUser);

        Delivery delivery = orderDto.getDelivery();
        order.setDelivery(delivery);

        order = orderService.save(order); // để tạo order.id => để các order detail có thể link đến

        CartDto cartDto = orderDto.getCart();

        // lưu order details
        // tăng thuộc tính sold của dish và lưu vào DB
        List<CartDetailDto> cartDetailDtos = cartDto.getCartDetails();
            for (CartDetailDto cartDetailDto : cartDetailDtos){
                OrderDetail orderDetail = new OrderDetail();
                Product product = cartDetailDto.getProduct();
                int quantity = cartDetailDto.getQuantity();
                orderDetail.setOrder(order);
                orderDetail.setProduct(product);
                orderDetail.setQuantity(quantity);
                orderDetailService.save(orderDetail);

                productService.save(product);
            }
            order.setServiceFee(cartDto.getServiceFee());
            order.setShippingFee(cartDto.getShippingFee());
            order.setDiscountAmount(cartDto.getDiscountAmount());
            order.setTotalFee(cartDto.getTotalFee());

            order.setMerchantNote("");
            order.setShippingNote("");
            order.setCoupon(null);

            order = orderService.save(order);
            order.setCreateDate(new Date());
            emptyCartById(orderDto.getCart().getId());
            return orderService.save(order);
    }

    @Override
    public void emptyCartById(Long cartId) {
        Optional<Cart> findCart = findById(cartId);
        if (findCart.isPresent()){
            cartDetailService.deleteAllByCart(findCart.get());
        }
    }
}
