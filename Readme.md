## 1. Cache-Aside

Read: Cache trước, DB sau (nếu cache miss).

Write: DB trước, xóa cache (invalidate) -> save vào cache ghi Read.


## 2. Write-Through

Read: Like Cache-Aside.

Write: DB trước, cache sau -> save vào cache khi write.

