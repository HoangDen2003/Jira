-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 05, 2024 lúc 04:34 AM
-- Phiên bản máy phục vụ: 10.4.32-MariaDB
-- Phiên bản PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `jira_permission_service`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_permissions`
--

CREATE TABLE `jira_permissions` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_permission_attribute`
--

CREATE TABLE `jira_permission_attribute` (
  `id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `attribute_key` varchar(255) NOT NULL,
  `attribute_value` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_permission_scheme`
--

CREATE TABLE `jira_permission_scheme` (
  `id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `permission_type` varchar(255) NOT NULL,
  `permission_parameter` varchar(255) NOT NULL,
  `permission_key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `jira_permissions`
--
ALTER TABLE `jira_permissions`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_permission_attribute`
--
ALTER TABLE `jira_permission_attribute`
  ADD PRIMARY KEY (`id`),
  ADD KEY `permission_id` (`permission_id`);

--
-- Chỉ mục cho bảng `jira_permission_scheme`
--
ALTER TABLE `jira_permission_scheme`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `jira_permissions`
--
ALTER TABLE `jira_permissions`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_permission_attribute`
--
ALTER TABLE `jira_permission_attribute`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_permission_scheme`
--
ALTER TABLE `jira_permission_scheme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `jira_permission_attribute`
--
ALTER TABLE `jira_permission_attribute`
  ADD CONSTRAINT `permission_id` FOREIGN KEY (`permission_id`) REFERENCES `jira_permissions` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
