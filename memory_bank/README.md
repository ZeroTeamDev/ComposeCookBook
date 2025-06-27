# Memory Bank System

Thư mục này chứa các file theo dõi tiến độ công việc trong các cuộc trò chuyện với AI. Mỗi file đại diện cho một workflow cụ thể và giúp AI theo dõi tiến trình làm việc liên tục.

## Cấu Trúc File

Mỗi file memory bank có định dạng: `wf_{tên_workflow}.md` và tuân theo cấu trúc:

```markdown
# Memory Bank: {Tên Workflow}

## Nhiệm Vụ Hiện Tại

- Task 1 từ yêu cầu người dùng
- Task 2 từ yêu cầu người dùng

## Kế Hoạch

Tóm tắt kế hoạch và suy nghĩ để giải quyết nhiệm vụ.

## Các Bước Thực Hiện

1. Bước 1
2. Bước 2
3. ...

## Việc Đã Hoàn Thành

- [x] Task 1
- [x] Task 2

## Việc Chưa Hoàn Thành

- Task 3
- Task 4

## Lưu Ý & Quyết Định

- Quyết định 1
- Lưu ý 1
```

## Quy Trình Sử Dụng

1. **Khởi tạo**: AI tự động tạo file memory bank khi bắt đầu một cuộc trò chuyện mới
2. **Cập nhật**: AI cập nhật tiến độ sau mỗi bước hoàn thành
3. **Đọc lại**: AI đọc memory bank trước mỗi tương tác mới
4. **Kết thúc**: Khi hoàn thành workflow, AI tổng kết và có thể chuyển thành entry trong `/experiences/`

## Lưu Ý

- Thư mục này đã được thêm vào `.gitignore`, nên các file không được theo dõi bởi Git
- Các file memory bank có thể bị xóa sau 30 ngày không sử dụng
- Memory bank quan trọng nên được chuyển vào hệ thống kinh nghiệm để lưu trữ lâu dài

## Mối Quan Hệ Với Experience System

Memory bank là bước trung gian trước khi tạo các entry trong hệ thống kinh nghiệm:

- **Memory Bank**: Theo dõi tiến độ trong một cuộc trò chuyện duy nhất
- **Experience System**: Lưu trữ kinh nghiệm lâu dài để tham khảo trong tương lai
