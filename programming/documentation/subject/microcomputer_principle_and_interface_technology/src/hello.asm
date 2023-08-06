.model small
.data
	msg db 'This is an example.$'
.stack
.code
start:
	mov ax, @data
	mov ds, ax
	mov dx, offset msg
	mov ah, 9
	int 21h
	mov ax, 4c00h
	int 21h
end start
