.model small
.data
	msg1 db 'Hello, ',0DH,0AH,'$' ;0DH,0AH表示换行
	msg2 db 'World! $'
.stack
.code
start:
	mov ax, @data
	mov ds, ax
	mov dx, offset msg1
	mov ah, 9
	int 21h
	mov dx, offset msg2
	mov ah, 9
	int 21h
	mov ax, 4C00H
	int 21h
end start
