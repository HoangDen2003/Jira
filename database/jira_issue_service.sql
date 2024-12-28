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
-- Cơ sở dữ liệu: `jira_issue_service`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_issues`
--

CREATE TABLE `jira_issues` (
  `id` int(11) NOT NULL,
  `pkey` varchar(255) NOT NULL,
  `issue_num` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `workflow_id` int(11) NOT NULL,
  `issue_type_id` int(11) NOT NULL,
  `issue_status_id` int(11) NOT NULL,
  `reporter` varchar(255) NOT NULL,
  `assignee` varchar(255) NOT NULL,
  `creator` varchar(255) NOT NULL,
  `summary` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `environment` varchar(255) NOT NULL,
  `watches` int(11) NOT NULL,
  `security` int(11) NOT NULL,
  `archived_by` varchar(255) DEFAULT NULL,
  `archived` varchar(255) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `due_date` timestamp NULL DEFAULT NULL,
  `archived_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_issue_status`
--

CREATE TABLE `jira_issue_status` (
  `id` int(11) NOT NULL,
  `sequence` int(11) DEFAULT NULL,
  `pname` varchar(255) NOT NULL,
  `icon_url` varchar(255) NOT NULL,
  `status_category` varchar(255) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_issue_types`
--

CREATE TABLE `jira_issue_types` (
  `id` int(11) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `pstyle` varchar(255) NOT NULL,
  `icon_url` varchar(255) NOT NULL,
  `avatar_id` int(11) NOT NULL,
  `description` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `jira_issues`
--
ALTER TABLE `jira_issues`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_issue_status`
--
ALTER TABLE `jira_issue_status`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_issue_types`
--
ALTER TABLE `jira_issue_types`
  ADD PRIMARY KEY (`id`),
  ADD KEY `avatar_id` (`avatar_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `jira_issues`
--
ALTER TABLE `jira_issues`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_issue_status`
--
ALTER TABLE `jira_issue_status`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_issue_types`
--
ALTER TABLE `jira_issue_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `jira_issue_types`
--
ALTER TABLE `jira_issue_types`
  ADD CONSTRAINT `avatar_id` FOREIGN KEY (`avatar_id`) REFERENCES `jira_avatar_service`.`jira_avatars` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
