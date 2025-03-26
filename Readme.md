## 1. Cache-Aside

Read: Cache trước, DB sau (nếu cache miss). chỉ lưu cache khi cache miss 

Write: DB trước, xóa cache (invalidate) -> save vào cache ghi Read.


## 2. Write-Through

Read: Like Cache-Aside.

Write: DB trước, cache sau -> save vào cache khi write.


## 2. Write-Around

Read: Like Cache-Aside.

Write: Chỉ work databasee

