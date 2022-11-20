# ShopBaeFood
## Ý tưởng
- Lấy cảm hứng từ ứng dụng ShopeeFood
- Website như một sàn thương mại cung cấp các dịch vụ như quản lý sản phẩm, quản lý đơn hàng để các shop có nhu cầu bán sản phẩm sử dụng.
- Người có nhu cầu mua hàng có thể thoả sức đặt hàng với nhiều mặt hàng ăn uống khác nhau mà không lo khoảng cách
## Công nghệ sử dụng
- Java spring boot, mySQL, spring security
## Các chức năng chính
- Người dùng có thể đăng ký làm thành viên của hệ thống
- Người bán có thể đăng ký gian hàng trên hệ thống
- Chức năng được phân chia dựa trên vai trò người sử dụng : ADMIN , Khách hàng, Shop
### ADMIN:
 - Quản lý , xem danh sách người mua hàng và shop
 - Duyệt , từ chối đăng ký thành shop bán hàng
 - Khóa , bỏ khóa các tài khoản người mua và shop
### USER:
- Tìm kiếm và xem các cửa hàng , gian hàng
- Tìm kiếm và xem các mặt hàng trong cửa hàng
- Đặt hàng , xem chi tiết , xác nhận đơn hàng
- Thiết lập thông tin người dùng shop
### MERCHANT
- Tìm kiếm và xem chi tiết các shop
- Quản lý sản phẩm của shop
- Quản lý đơn hàng của khách hàng
- Thiết lập thông tin shop
- Thống kê lượng hàng bán, lượng hàng tồn kho
