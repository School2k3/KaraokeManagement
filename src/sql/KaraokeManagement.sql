USE [master]
GO
/****** Object:  Database [KaraokeManagement]    Script Date: 25/10/2023 1:28:50 PM ******/
CREATE DATABASE [KaraokeManagement]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'KaraokeManagement', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\KaraokeManagement.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'KaraokeManagement_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL15.SQLEXPRESS\MSSQL\DATA\KaraokeManagement_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [KaraokeManagement] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [KaraokeManagement].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [KaraokeManagement] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [KaraokeManagement] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [KaraokeManagement] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [KaraokeManagement] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [KaraokeManagement] SET ARITHABORT OFF 
GO
ALTER DATABASE [KaraokeManagement] SET AUTO_CLOSE ON 
GO
ALTER DATABASE [KaraokeManagement] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [KaraokeManagement] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [KaraokeManagement] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [KaraokeManagement] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [KaraokeManagement] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [KaraokeManagement] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [KaraokeManagement] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [KaraokeManagement] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [KaraokeManagement] SET  ENABLE_BROKER 
GO
ALTER DATABASE [KaraokeManagement] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [KaraokeManagement] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [KaraokeManagement] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [KaraokeManagement] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [KaraokeManagement] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [KaraokeManagement] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [KaraokeManagement] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [KaraokeManagement] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [KaraokeManagement] SET  MULTI_USER 
GO
ALTER DATABASE [KaraokeManagement] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [KaraokeManagement] SET DB_CHAINING OFF 
GO
ALTER DATABASE [KaraokeManagement] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [KaraokeManagement] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [KaraokeManagement] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [KaraokeManagement] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
ALTER DATABASE [KaraokeManagement] SET QUERY_STORE = OFF
GO
USE [KaraokeManagement]
GO
/****** Object:  Table [dbo].[ChiTietHoaDon]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietHoaDon](
	[maHoaDon] [nvarchar](6) NOT NULL,
	[maDichVu] [nvarchar](3) NOT NULL,
	[donGia] [float] NULL,
	[thoiGianBatDau] [datetime] NULL,
	[thoiGianKetThuc] [datetime] NULL,
 CONSTRAINT [pk_ChiTietHoaDon] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC,
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuDatPhong]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuDatPhong](
	[maPhieuDat] [nvarchar](7) NOT NULL,
	[maPhong] [nvarchar](3) NOT NULL,
	[ngayDat] [date] NULL,
	[gioDat] [datetime] NULL,
 CONSTRAINT [pk_ChiTietPhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhieuDat] ASC,
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ChiTietPhieuDichVu]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ChiTietPhieuDichVu](
	[maPhieuDichVu] [nvarchar](7) NOT NULL,
	[maDichVu] [nvarchar](3) NOT NULL,
	[soLuong] [int] NULL,
	[donGia] [float] NULL,
 CONSTRAINT [pk_ChiTietPhieuDichVu] PRIMARY KEY CLUSTERED 
(
	[maPhieuDichVu] ASC,
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[DichVu]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[DichVu](
	[maDichVu] [nvarchar](3) NOT NULL,
	[tenDichVu] [nvarchar](50) NULL,
	[soLuongTon] [int] NULL,
	[maLoaiDichVu] [nvarchar](10) NULL,
	[donGia] [float] NULL,
 CONSTRAINT [pk_DichVu] PRIMARY KEY CLUSTERED 
(
	[maDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[HoaDon]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[HoaDon](
	[maHoaDon] [nvarchar](6) NOT NULL,
	[maKhachHang] [nvarchar](7) NULL,
	[maNhanVien] [nvarchar](5) NULL,
	[maPhong] [nvarchar](3) NULL,
	[ngayLap] [date] NULL,
	[trangThai] [nvarchar](30) NULL,
 CONSTRAINT [pk_HoaDon] PRIMARY KEY CLUSTERED 
(
	[maHoaDon] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[KhachHang]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[KhachHang](
	[maKhachHang] [nvarchar](7) NOT NULL,
	[hoTenKhachHang] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[soDienThoai] [nvarchar](10) NOT NULL,
	[canCuocCongDan] [nvarchar](12) NOT NULL,
 CONSTRAINT [pk_KhachHang] PRIMARY KEY CLUSTERED 
(
	[maKhachHang] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[soDienThoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[canCuocCongDan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiDichVu]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiDichVu](
	[maLoaiDichVu] [nvarchar](10) NOT NULL,
	[tenLoaiDichVu] [nvarchar](50) NULL,
 CONSTRAINT [pk_LoaiDichVu] PRIMARY KEY CLUSTERED 
(
	[maLoaiDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[LoaiPhong]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[LoaiPhong](
	[maLoaiPhong] [nvarchar](3) NOT NULL,
	[tenLoaiPhong] [nvarchar](50) NULL,
 CONSTRAINT [pk_LoaiPhong] PRIMARY KEY CLUSTERED 
(
	[maLoaiPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[NhanVien]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NhanVien](
	[maNhanVien] [nvarchar](5) NOT NULL,
	[hoTenNhanVien] [nvarchar](50) NULL,
	[gioiTinh] [bit] NULL,
	[namSinh] [int] NULL,
	[diaChi] [nvarchar](80) NULL,
	[soDienThoai] [nvarchar](10) NOT NULL,
	[canCuocCongDan] [nvarchar](12) NOT NULL,
	[matKhau] [nvarchar](20) NULL,
	[chucVu] [bit] NULL,
 CONSTRAINT [pk_NhanVien] PRIMARY KEY CLUSTERED 
(
	[maNhanVien] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[soDienThoai] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
UNIQUE NONCLUSTERED 
(
	[canCuocCongDan] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDatPhong]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDatPhong](
	[maPhieuDat] [nvarchar](7) NOT NULL,
	[maKhachHang] [nvarchar](7) NULL,
	[maNhanVien] [nvarchar](5) NULL,
	[ngayLap] [date] NULL,
	[trangThai] [nvarchar](30) NULL,
 CONSTRAINT [pk_PhieuDatPhong] PRIMARY KEY CLUSTERED 
(
	[maPhieuDat] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[PhieuDichVu]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[PhieuDichVu](
	[maPhieuDichVu] [nvarchar](7) NOT NULL,
	[maKhachHang] [nvarchar](7) NULL,
	[maNhanVien] [nvarchar](5) NULL,
	[ngayLap] [date] NULL,
	[trangThai] [nvarchar](30) NULL,
 CONSTRAINT [pk_PhieuDichVu] PRIMARY KEY CLUSTERED 
(
	[maPhieuDichVu] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Phong]    Script Date: 25/10/2023 1:28:50 PM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Phong](
	[maPhong] [nvarchar](3) NOT NULL,
	[tenPhong] [nvarchar](50) NULL,
	[trangThai] [nvarchar](50) NULL,
	[maLoaiPhong] [nvarchar](3) NULL,
	[donGia] [float] NULL,
 CONSTRAINT [pk_Phong] PRIMARY KEY CLUSTERED 
(
	[maPhong] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietHoaDon_DichVu] FOREIGN KEY([maDichVu])
REFERENCES [dbo].[DichVu] ([maDichVu])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [fk_ChiTietHoaDon_DichVu]
GO
ALTER TABLE [dbo].[ChiTietHoaDon]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietHoaDon_HoaDon] FOREIGN KEY([maHoaDon])
REFERENCES [dbo].[HoaDon] ([maHoaDon])
GO
ALTER TABLE [dbo].[ChiTietHoaDon] CHECK CONSTRAINT [fk_ChiTietHoaDon_HoaDon]
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietPhieuDatPhong_PhieuDatPhong] FOREIGN KEY([maPhieuDat])
REFERENCES [dbo].[PhieuDatPhong] ([maPhieuDat])
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong] CHECK CONSTRAINT [fk_ChiTietPhieuDatPhong_PhieuDatPhong]
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietPhieuDatPhong_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[ChiTietPhieuDatPhong] CHECK CONSTRAINT [fk_ChiTietPhieuDatPhong_Phong]
GO
ALTER TABLE [dbo].[ChiTietPhieuDichVu]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietPhieuDichVu_DichVu] FOREIGN KEY([maDichVu])
REFERENCES [dbo].[DichVu] ([maDichVu])
GO
ALTER TABLE [dbo].[ChiTietPhieuDichVu] CHECK CONSTRAINT [fk_ChiTietPhieuDichVu_DichVu]
GO
ALTER TABLE [dbo].[ChiTietPhieuDichVu]  WITH CHECK ADD  CONSTRAINT [fk_ChiTietPhieuDichVu_PhieuDichVu] FOREIGN KEY([maPhieuDichVu])
REFERENCES [dbo].[PhieuDichVu] ([maPhieuDichVu])
GO
ALTER TABLE [dbo].[ChiTietPhieuDichVu] CHECK CONSTRAINT [fk_ChiTietPhieuDichVu_PhieuDichVu]
GO
ALTER TABLE [dbo].[DichVu]  WITH CHECK ADD  CONSTRAINT [fk_DichVu_LoaiDichVu] FOREIGN KEY([maLoaiDichVu])
REFERENCES [dbo].[LoaiDichVu] ([maLoaiDichVu])
GO
ALTER TABLE [dbo].[DichVu] CHECK CONSTRAINT [fk_DichVu_LoaiDichVu]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [fk_HoaDon_KhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [fk_HoaDon_KhachHang]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [fk_HoaDon_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [fk_HoaDon_NhanVien]
GO
ALTER TABLE [dbo].[HoaDon]  WITH CHECK ADD  CONSTRAINT [fk_HoaDon_Phong] FOREIGN KEY([maPhong])
REFERENCES [dbo].[Phong] ([maPhong])
GO
ALTER TABLE [dbo].[HoaDon] CHECK CONSTRAINT [fk_HoaDon_Phong]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [fk_PhieuDatPhong_KhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [fk_PhieuDatPhong_KhachHang]
GO
ALTER TABLE [dbo].[PhieuDatPhong]  WITH CHECK ADD  CONSTRAINT [fk_PhieuDatPhong_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[PhieuDatPhong] CHECK CONSTRAINT [fk_PhieuDatPhong_NhanVien]
GO
ALTER TABLE [dbo].[PhieuDichVu]  WITH CHECK ADD  CONSTRAINT [fk_PhieuDichVu_KhachHang] FOREIGN KEY([maKhachHang])
REFERENCES [dbo].[KhachHang] ([maKhachHang])
GO
ALTER TABLE [dbo].[PhieuDichVu] CHECK CONSTRAINT [fk_PhieuDichVu_KhachHang]
GO
ALTER TABLE [dbo].[PhieuDichVu]  WITH CHECK ADD  CONSTRAINT [fk_PhieuDichVu_NhanVien] FOREIGN KEY([maNhanVien])
REFERENCES [dbo].[NhanVien] ([maNhanVien])
GO
ALTER TABLE [dbo].[PhieuDichVu] CHECK CONSTRAINT [fk_PhieuDichVu_NhanVien]
GO
ALTER TABLE [dbo].[Phong]  WITH CHECK ADD  CONSTRAINT [fk_Phong_LoaiPhong] FOREIGN KEY([maLoaiPhong])
REFERENCES [dbo].[LoaiPhong] ([maLoaiPhong])
GO
ALTER TABLE [dbo].[Phong] CHECK CONSTRAINT [fk_Phong_LoaiPhong]
GO
USE [master]
GO
ALTER DATABASE [KaraokeManagement] SET  READ_WRITE 
GO
