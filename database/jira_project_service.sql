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
-- Cơ sở dữ liệu: `jira_project_service`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_projects`
--

CREATE TABLE `jira_projects` (
  `id` int(11) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  `lead` varchar(255) NOT NULL,
  `description` text NOT NULL,
  `pkey` int(11) NOT NULL,
  `pcounter` int(11) NOT NULL,
  `assignee_type` varchar(255) NOT NULL,
  `avatar_id` int(11) NOT NULL,
  `original_key` varchar(255) NOT NULL,
  `project_type_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_project_keys`
--

CREATE TABLE `jira_project_keys` (
  `id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `project_key` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_project_types`
--

CREATE TABLE `jira_project_types` (
  `id` int(11) NOT NULL,
  `pt_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_project_user`
--

CREATE TABLE `jira_project_user` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `project_id` int(11) NOT NULL,
  `project_role_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `jira_projects`
--
ALTER TABLE `jira_projects`
  ADD PRIMARY KEY (`id`),
  ADD KEY `avatar_id` (`avatar_id`),
  ADD KEY `project_type_id` (`project_type_id`);

--
-- Chỉ mục cho bảng `jira_project_keys`
--
ALTER TABLE `jira_project_keys`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_project_types`
--
ALTER TABLE `jira_project_types`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_project_user`
--
ALTER TABLE `jira_project_user`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `project_id` (`project_id`),
  ADD KEY `project_role_id` (`project_role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `jira_projects`
--
ALTER TABLE `jira_projects`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_project_keys`
--
ALTER TABLE `jira_project_keys`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_project_types`
--
ALTER TABLE `jira_project_types`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_project_user`
--
ALTER TABLE `jira_project_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `jira_projects`
--
ALTER TABLE `jira_projects`
  ADD CONSTRAINT `avatar_id` FOREIGN KEY (`avatar_id`) REFERENCES `jira_avatar_service`.`jira_avatars` (`id`),
  ADD CONSTRAINT `project_type_id` FOREIGN KEY (`project_type_id`) REFERENCES `jira_project_types` (`id`);

--
-- Các ràng buộc cho bảng `jira_project_user`
--
ALTER TABLE `jira_project_user`
  ADD CONSTRAINT `project_id` FOREIGN KEY (`project_id`) REFERENCES `jira_projects` (`id`),
  ADD CONSTRAINT `project_role_id` FOREIGN KEY (`project_role_id`) REFERENCES `jira_identity_service`.`jira_project_roles` (`id`),
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `jira_identity_service`.`jira_users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
