-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 05, 2024 lúc 04:33 AM
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
-- Cơ sở dữ liệu: `jira_identity_service`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_global_roles`
--

CREATE TABLE `jira_global_roles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `desription` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `jira_global_roles`
--

INSERT INTO `jira_global_roles` (`id`, `name`, `desription`) VALUES
(1, 'super_admin', 'Quản lý các user'),
(2, 'user', ''),
(3, 'guest', 'Đây là người dùng chưa được xác thực');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_project_roles`
--

CREATE TABLE `jira_project_roles` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `desription` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `jira_project_roles`
--

INSERT INTO `jira_project_roles` (`id`, `name`, `desription`) VALUES
(1, 'administrator', ''),
(2, 'member', ''),
(3, 'viewer', '');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_remember_me_tokens`
--

CREATE TABLE `jira_remember_me_tokens` (
  `id` int(11) NOT NULL,
  `token` varchar(255) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_users`
--

CREATE TABLE `jira_users` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `global_role_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `jira_user_profiles`
--

CREATE TABLE `jira_user_profiles` (
  `id` int(11) NOT NULL,
  `avatar_id` int(11) NOT NULL,
  `full_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  `birthday` date NOT NULL,
  `gender` enum('male','female','other') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `jira_global_roles`
--
ALTER TABLE `jira_global_roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_project_roles`
--
ALTER TABLE `jira_project_roles`
  ADD PRIMARY KEY (`id`);

--
-- Chỉ mục cho bảng `jira_remember_me_tokens`
--
ALTER TABLE `jira_remember_me_tokens`
  ADD PRIMARY KEY (`id`),
  ADD KEY `user_id` (`user_id`);

--
-- Chỉ mục cho bảng `jira_users`
--
ALTER TABLE `jira_users`
  ADD PRIMARY KEY (`id`),
  ADD KEY `global_role_id` (`global_role_id`);

--
-- Chỉ mục cho bảng `jira_user_profiles`
--
ALTER TABLE `jira_user_profiles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `avatar_id` (`avatar_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `jira_global_roles`
--
ALTER TABLE `jira_global_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `jira_project_roles`
--
ALTER TABLE `jira_project_roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `jira_remember_me_tokens`
--
ALTER TABLE `jira_remember_me_tokens`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_users`
--
ALTER TABLE `jira_users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `jira_user_profiles`
--
ALTER TABLE `jira_user_profiles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `jira_remember_me_tokens`
--
ALTER TABLE `jira_remember_me_tokens`
  ADD CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `jira_users` (`id`);

--
-- Các ràng buộc cho bảng `jira_users`
--
ALTER TABLE `jira_users`
  ADD CONSTRAINT `global_role_id` FOREIGN KEY (`global_role_id`) REFERENCES `jira_global_roles` (`id`);

--
-- Các ràng buộc cho bảng `jira_user_profiles`
--
ALTER TABLE `jira_user_profiles`
  ADD CONSTRAINT `avatar_id` FOREIGN KEY (`avatar_id`) REFERENCES `jira_avatar_service`.`jira_avatars` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
