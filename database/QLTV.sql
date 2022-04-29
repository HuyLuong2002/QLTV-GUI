﻿CREATE database QLTV
use QLTV
CREATE TABLE NXB
(
	MANXB nchar(6) not null primary key,
	TENNXB nvarchar(40) not null,
)
CREATE TABLE THELOAI
(
	MATL nchar(5) not null primary key,
	TENTL nvarchar(30) not null,
	SLTL int,
)
CREATE TABLE TACGIA
(
	MATG nchar(5) not null primary key,
	TENTG nvarchar(30) not null,
)
CREATE TABLE NHACUNGCAP
(
	MANCC nchar(6) not null primary key,
	TENNCC nvarchar(30) not null,
)
CREATE TABLE SACH
(
	MASACH nchar(5) not null primary key,
	TENSACH nvarchar(30) not null,
	MANXB nchar(6) not null foreign key references NXB(MANXB),
	MATG nchar(5) not null foreign key references TACGIA(MATG),
	NAMXB nchar(5),
	SLTONG int,
	SL int,
	DONGIA int,
)
CREATE TABLE DOCGIA
(
	MADG nchar(5) not null primary key,
	TENDG nvarchar(30) not null,
	DIACHI nvarchar(50) not null,
	EMAIL nchar(20) not null,
	TINHTRANGTHUE nvarchar(10) not null,
)
CREATE TABLE PHIEUTHEODOIMT
(
	MADG nchar(5) not null primary key,
	TONGMUON int not null,
	TIENCOC int,
)
CREATE TABLE PHIEUMUON
(
	MAPM nchar(5) not null primary key,
	NGAYMUON date,
	SLTONG int,
	NGAYTRA date,
	TINHTRANGMUON nvarchar(10),
	MADG nchar(5) not null foreign key references DOCGIA(MADG),
)
CREATE TABLE CHITIETPHIEUMUON
(
	MAPM nchar(5) not null,
	MASACH nchar(5) not null,
	SL int,
	PRIMARY KEY (MAPM,MASACH)
)
CREATE TABLE PHIEUTRASACH
(
	MAPT nchar(5) not null,
	NGAYTRA date,
	TIENTHUE int,
	THANHTIEN int,
	MAPM nchar(5) not null,
	PRIMARY KEY (MAPT,MAPM)
)
CREATE TABLE CHITIETPHIEUTRA
(
	MAPT nchar(5) not null,
	MASACH nchar(5) not null,
	SL int,
	PRIMARY KEY (MAPT, MASACH)
)
CREATE TABLE HDTIENPHAT
(
	MAHD nchar(5) not null,
	MASACH nchar(5) not null,
	SL int,
	TIENPHAT int,
	PRIMARY KEY (MAHD,MASACH)
)
CREATE TABLE NHANVIEN
(
	MANV nchar(5) not null primary key,
	TENNV nvarchar(30) not null,
	CHUCVU nvarchar(20) not null,
	LUONGCB int,
	PHUCAP int,
	HESOLUONG float,
	SDT nchar(10),
	EMAIL nchar(20),
)
CREATE TABLE PHIEUNHAP
(
	MAPN nchar(5) not null primary key,
	NGAYNHAP date,
	SLTONG int,
	DONGIA int,
	MANV nchar(5) not null foreign key references NHANVIEN(MANV),
	MANCC nchar(6) not null foreign key references NHACUNGCAP(MANCC),
)
CREATE TABLE CHITIETPHIEUNHAP
(
	MAPN nchar(5) not null,
	MASACH nchar(30) not null,
	SL int,
	PRIMARY KEY (MAPN,MASACH),
)
--Thêm dữ liệu--
--NXB--
INSERT INTO NXB VALUES ('NXB001',N'Nhà xuất bản Thanh Niên')
INSERT INTO NXB VALUES ('NXB002',N'Nhà xuất bản Trẻ')
INSERT INTO NXB VALUES ('NXB003',N'Nhà xuất bản Văn Hóa')
INSERT INTO NXB VALUES ('NXB004',N'Nhà xuất bản Giáo Dục Việt Nam')
INSERT INTO NXB VALUES ('NXB005',N'Nhà xuất bản Tuổi Trẻ')
--THELOAI--
INSERT INTO THELOAI VALUES ('TL001',N'Giáo trình',5)
INSERT INTO THELOAI VALUES ('TL002',N'Kinh tế',10)
INSERT INTO THELOAI VALUES ('TL003',N'Tạp chí, báo',8)
INSERT INTO THELOAI VALUES ('TL004',N'Giáo khoa',20)
INSERT INTO THELOAI VALUES ('TL005',N'Ngoại ngữ',35)
--TACGIA--
INSERT INTO TACGIA VALUES ('TG001',N'Nguyễn Văn A')
INSERT INTO TACGIA VALUES ('TG002',N'Nguyễn Văn B')
INSERT INTO TACGIA VALUES ('TG003',N'Nguyễn Văn C')
INSERT INTO TACGIA VALUES ('TG004',N'Nguyễn Văn D')
INSERT INTO TACGIA VALUES ('TG005',N'Nguyễn Văn E')
--NHACUNGCAP--
INSERT INTO NHACUNGCAP VALUES ('NCC001',N'Nhà sách Fahasa')
INSERT INTO NHACUNGCAP VALUES ('NCC002',N'Nhà sách Phương Nam')
INSERT INTO NHACUNGCAP VALUES ('NCC003',N'Nhà sách Nguyễn Văn Cừ')
INSERT INTO NHACUNGCAP VALUES ('NCC004',N'Nhà sách Mcbooks')
INSERT INTO NHACUNGCAP VALUES ('NCC005',N'Nhà sách GDbooks')
--SACH--
INSERT INTO SACH VALUES ('MS001',N'Đời ngắn đừng ngủ dài','NXB001','TG001','2003',10,10,15000)
INSERT INTO SACH VALUES ('MS002',N'Giáo trình toán rời rạc','NXB002','TG002','2003',50,5,30000)
INSERT INTO SACH VALUES ('MS003',N'Giáo trình kỹ thuật lập trình','NXB003','TG003','2003',20,10,25000)
INSERT INTO SACH VALUES ('MS004',N'Giải tích 1','NXB004','TG002','2003',10,10,30000)
INSERT INTO SACH VALUES ('MS005',N'Giải tích 2','NXB005','TG004','2003',5,5,26000)
--DOCGIA--
INSERT INTO DOCGIA VALUES ('DG001',N'Trần Văn B',N'25/3 Lạc Long Quân, Q.10, TP HCM','tranvanb@gmail.com','Đang thuê')
INSERT INTO DOCGIA VALUES ('DG002',N'Nguyễn Văn L',N'125 Trần Hưng Đạo, Q.1, TP HCM','nguyenvanl@gmail.com','Hết thuê')
INSERT INTO DOCGIA VALUES ('DG003',N'Phạm Văn C',N'12/21 Võ Văn Ngân, Thủ Đức, TP HCM','phamvanc@gmail.com','Đang thuê')
INSERT INTO DOCGIA VALUES ('DG004',N'Lưu Văn F',N'221 Hùng Vương, Q.5, TP HCM','luuvanf@gmail.com','Hết thuê')
INSERT INTO DOCGIA VALUES ('DG005',N'Trần Văn A',N'127 Hùng Vương, TP Mỹ Tho','tranvana@gmail.com','Đang thuê')
--PHIEUTHEODOIMT--
INSERT INTO PHIEUTHEODOIMT VALUES ('DG001','4',12000)
INSERT INTO PHIEUTHEODOIMT VALUES ('DG003','5',15000)
INSERT INTO PHIEUTHEODOIMT VALUES ('DG002','7',21000)
INSERT INTO PHIEUTHEODOIMT VALUES ('DG005','3',9000)
INSERT INTO PHIEUTHEODOIMT VALUES ('DG004','5',15000)
--PHIEUMUON--
INSERT INTO PHIEUMUON VALUES ('PM001','2022/03/05',4,'2022/03/20',N'Đang mượn','DG002')
INSERT INTO PHIEUMUON VALUES ('PM002','2022/03/04',4,'2022/03/19',N'Đang mượn','DG001')
INSERT INTO PHIEUMUON VALUES ('PM003','2022/04/15',5,'2022/04/30',N'Đang mượn','DG004')
INSERT INTO PHIEUMUON VALUES ('PM004','2022/04/10',3,'2022/04/25',N'Đang mượn','DG002')
INSERT INTO PHIEUMUON VALUES ('PM005','2022/04/02',3,'2022/04/17',N'Đang mượn','DG005')
--CHITIETPHIEUMUON--
INSERT INTO CHITIETPHIEUMUON VALUES ('PM001','MS001',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM001','MS002',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM001','MS003',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM001','MS004',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM002','MS002',4)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM003','MS005',2)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM003','MS002',2)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM003','MS003',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM004','MS001',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM004','MS005',2)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM005','MS005',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM005','MS002',1)
INSERT INTO CHITIETPHIEUMUON VALUES ('PM005','MS003',1)
--PHIEUTRASACH--
INSERT INTO PHIEUTRASACH VALUES ('PT001','2022/03/20',3000,12000,'PM001')
INSERT INTO PHIEUTRASACH VALUES ('PT002','2022/03/19',3000,12000,'PM002')
INSERT INTO PHIEUTRASACH VALUES ('PT003','2022/04/30',3000,15000,'PM003')
INSERT INTO PHIEUTRASACH VALUES ('PT004','2022/04/25',3000,9000,'PM004')
INSERT INTO PHIEUTRASACH VALUES ('PT005','2022/04/17',3000,9000,'PM005')
--CHITIETPHIEUTRA--
INSERT INTO CHITIETPHIEUTRA VALUES ('PT001','MS001',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT001','MS002',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT001','MS003',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT001','MS004',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT002','MS002',4)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT003','MS005',2)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT003','MS002',2)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT003','MS003',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT004','MS001',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT004','MS005',2)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT005','MS005',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT005','MS002',1)
INSERT INTO CHITIETPHIEUTRA VALUES ('PT005','MS003',1)
--HDTIENPHAT--
INSERT INTO HDTIENPHAT VALUES ('HD001','MS002',2,30000)
INSERT INTO HDTIENPHAT VALUES ('HD002','MS001',3,45000)
INSERT INTO HDTIENPHAT VALUES ('HD003','MS003',1,20000)
INSERT INTO HDTIENPHAT VALUES ('HD004','MS004',1,30000)
INSERT INTO HDTIENPHAT VALUES ('HD005','MS005',2,30000)
--NHANVIEN--
INSERT INTO NHANVIEN VALUES ('NV001','Nguyễn Văn A','Giám đốc',30000000,5000000,3.0,0915656572,'nguyenvana@gmail.com')
INSERT INTO NHANVIEN VALUES ('NV002','Nguyễn Văn B','Thủ thư',5000000,120000,2.0,0915656432,'nguyenvanb@gmail.com')
INSERT INTO NHANVIEN VALUES ('NV003','Nguyễn Văn C','Bảo vệ',5000000,120000,2.0,0915456572,'nguyenvanc@gmail.com')
--PHIEUNHAP--
INSERT INTO PHIEUNHAP VALUES ('PN001','2022/03/18',15,500000,'NV002','NCC001')
INSERT INTO PHIEUNHAP VALUES ('PN002','2022/03/18',20,600000,'NV002','NCC002')
INSERT INTO PHIEUNHAP VALUES ('PN003','2022/03/20',30,3500000,'NV002','NCC003')
INSERT INTO PHIEUNHAP VALUES ('PN004','2022/03/22',60,6000000,'NV002','NCC005')
INSERT INTO PHIEUNHAP VALUES ('PN005','2022/03/23',70,10500000,'NV002','NCC004')
--CHITIETPHIEUNHAP--
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN001','MS001',10)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN001','MS002',5)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN002','MS003',10)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN002','MS004',10)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN003','MS004',30)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN004','MS005',60)
INSERT INTO CHITIETPHIEUNHAP VALUES ('PN005','MS001',70)

/*
DROP TABLE NXB
DROP TABLE THELOAI
DROP TABLE TACGIA
DROP TABLE NHACUNGCAP
DROP TABLE SACH
DROP TABLE DOCGIA
DROP TABLE PHIEUTHEODOIMT
DROP TABLE PHIEUMUON
DROP TABLE CHITIETPHIEUMUON
DROP TABLE PHIEUTRASACH
DROP TABLE CHITIETPHIEUTRA
DROP TABLE HDTIENPHAT
DROP TABLE NHANVIEN
DROP TABLE PHIEUNHAP
DROP TABLE CHITIETPHIEUNHAP
*/