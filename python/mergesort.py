
def merge_sort(a,left,right):
    if left >= right:
        return
    mid = int((left+right)/2)
    merge_sort(a, left, mid)
    merge_sort(a, mid+1, right)
    (i, j, k, n)=(left, mid+1, 0, right-left+1)
    temp = n * [0]
    while i <= mid and j <= right:
        (temp[k], i, j, k) = (a[i], i+1, j, k+1) if a[i] < a[j] else (a[j], i, j+1, k+1)
    while i <= mid:
        (temp[k], i, k) = (a[i], i+1, k+1)
    while j<=right:
        (temp[k], j, k) = (a[j], j+1, k+1)
    for i in range(n):
        a[left+i] = temp[i]

if __name__ == "__main__":
    arr = [55, 11, 99, 33, 77, 22, 66, 44, 88]
    print(arr)
    merge_sort(arr, 0, len(arr)-1)
    print(arr)
