-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-04-2023 a las 01:41:49
-- Versión del servidor: 10.4.27-MariaDB
-- Versión de PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `registroestudiantes`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `cedula` char(10) NOT NULL,
  `usuario` varchar(45) DEFAULT NULL,
  `clave` varchar(255) DEFAULT NULL,
  `huella` blob NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) DEFAULT NULL,
  `telefono` char(10) DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `root` int(1) DEFAULT NULL,
  `estado` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`cedula`, `usuario`, `clave`, `huella`, `nombre`, `apellido`, `telefono`, `direccion`, `root`, `estado`) VALUES
('0503259590', 'sgdsfgd9590', 'dutmveAitP88Rpjcic+u5g==', 0x00f87f01c82ae3735cc0413709ab71308f145592e7939d184158a837264a8f5cf760f44d16337b3c82ee9f80eb7375e7885009072e63eacc310d941e71531d1861155e440b36a7556c680fd8928d324d2908e1dd41a67187672f6e76f8b3a0bb8555957334e690a0dc28686df65c796c10a87230831873f770fd77e0ebafbe8fcfad6d287a506c8de633d161ed47aacbfaab769923df9915a387345cab95a62d0eb46a40e4534308c10e6c303dcaa392266b79f04b2d127207b035fb3ea9172485bb3d7a9ba6c409e82742a6f3646c26e0f1eeed6fe9ef2818e4e0c486feed2b2a130099c0014b07329ed004a971f4d7a67b15b0003ff78c64dd490a0af4e064e773df04b78104446913365c6ed5ef6dde96ccb3e450a8ce9e8b10aee418ca02a75652e38303e7efa85e7292415c350528df901dc5699444da9e9bc44d25972e77bc6011814deedff2ce8fbbb199eb887bd0b70abdc63d81b0f0164692fbdcc47da50ccf6b9370856f77d80232dfd6af7ed87bb9b66a0fdd6e0b8ece5e326014da1fb76f00f87f01c82ae3735cc0413709ab71708b145592020f681c843829c6eb66257d01f7d27bb9db9bfacd19d31aefd44c12be7d6f4a4d4d79bea36ea87ca340ae5c4ffb42df20997af7c8ef371a80832651e81a06af33f892c4a4f55c0a1d3106f8f8e7be31c40f8285a121190d1fed2f5dd0c6f87b38a50867d534a0100e197514e346212498017ffc15a17acf90038e9ced4c1b030f64abf943258de3633c7f3fac732169f7f02b5a7140165f1231107abb6f945c68f889a39c9ef1a6e728c10a10fbd7e341b9f057daee65f666b6e9184a2fa6485fcb053cc5c1131428c2d469ced4af7e0482908d63cbf674c348f01f3917646477bd5107bd2c1b2ef155246196f9b87ec41e8a8d770a6846077ec3958ec57d8818ab26113783aec9e35e33e33a973c52bffe3775dcad3ff581bd5a3f95a3ee026032b522327b0f9cbd36f3df9bceb738bd7205156a48ddf912f2647ffc609464ae2fee8e31e5ff1d5714cfce88040cc05429a3908ff5aaf2632b0916ce2d0b57bd5f3ad4635dfa11b7e853b127205c6f00f87e01c82ae3735cc0413709ab7130ab14559282c71f82e59ddbb0950a681acd4d553876a64c9ac848dfe13c21fa05bb04f1cd6bc875b447730e638da565ecf28fbc4d00db7fca26a7436927710e3f8785297b0d931ea8b1b38854908676ebe7e103ee66e468b9a0ea6ad2884c68bc939746822a4c83fede9cf03eebd1b96414b43bc052eec18e6044628d85efee1c4b9db5bc2a1e0206c8f0d9cd2db08a2f4c1200a49495ecc06a676472f76cb2624e12de623d5566bf0818fac5996fd12388349c2daa2af9a26935255de4997305a414545f3db0040875af0596d83bce36a90389d8101e70cfd77c20bf59b509d22ead68cd68126fcdb9f222c9cdefc2a666c1b3b98eca5ea34a437ea91d6f369327446f75badd2a91047444d267433a4f10d1d00331731afa3cd48bbe6ec746e1e44fbbea5fd12c27e94d37658afda5ac12d174b099bd449f80e4ede2a1af7623fdfbdd25fd7d2c977d50e526873d82526681c6da6891e51fa438a294761dfa823845d061953e2ea89683fd85a2280dd8c5b46f00e87e01c82ae3735cc0413709ab71f0f8145592b8617e76636b8985f2c0da3377ef29159ff72a68235481744e2f528f3ce368b18a8a6903e7887396be25afca745d90d133dfc6ebb67fcc81f9e44bcc09e562a30e6325771b385c516666b5ba70300d545a18ac6feb61c4ee0d357dedc7f5c2a849f169c16161e2c12d4d1a5453020011d5e5dbd7633de44f1a30986a6748140c2ae461d78c97d8ef6541bed3c55081b563a6bf59e380d92a69a8001edaa90b4765f0ec146e58ec807ec53701e3e6c689238fdc028dc7478b3e2d8b626fe59a6cdf7a6446c3a1aca6ab1813622b4614617fc0a53d1d4125a998fbb49527fb0eef4f341796c5ccf7d7b3c732b6f16030f9a8731678fbacaa2620856e82d68351c89a05038bf0e3b9d2aca52c2f7d401cba380730012a14f89ec31c27f417e4243011c1f30355fd2bc93899faa25afdbdf774ec5c88be496dc0e26374d1cf6864ca8d357866e2b66f12c1c35da75c7aef0744c0e7104c70ae1d2982ba63cf7b922f266499391937c02b0a226c3ef96a6f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'sgfdgs', 'gdsfgd', '123', 'dfsd', 0, 1),
('1722818661', 'epila8661', '95bSfvoQFlKOATaY77NU5A==', 0x00f88001c82ae3735cc0413709ab71b0ab1455928d679cada14fb1025cf1708aeac8fe5e1e3820e1daa322b11b68f0ca62a2f33dfa46492d5bc7499263c2ee054a13be198ab441b17e61a2472d688cd29cb55d869143f9169577b82fbf228e0005d4c9afce3bb6cc6978fc927270b1a3ae170e44d6047200881a68f6ef8dafd386a69f412c29988414632dc308b8199fe0c08e131bf2426b6535f7173f46fdfd8b4fe292343a24413a730e3e0696a0959bb851ae9443314eaa597c22573bfe7c93201c7562373c22924d6e33c2d55dd9302a59ebd104d4c50903824cf53fe3176800d91209e29733b0f4517d56cee6659294cdfb6445725661395ed031e253a530b93bb16633250189edbb285767f13cbe7b7577d966f28bc05f47898a890da443cf758a8b416925f1a297be1bec98a53701b24f3ebd45c2fcdb60991d72961165b11e0f1c37629a74f2e7589297b61361d843de98f05ce097193ab50768a9f8e71e9397d2cead2c172bc2e0c8c5b14dc70da675f0f122f041d7f1aa3fc85b83129a8c826f00f87e01c82ae3735cc041234d52541b10f996443fc4bd43ae7a1ea4adb4257069f9dc69d38d09ebdb8afa2d1260111b3c606910de999d00b67ca15f55f4396b8b0942602aeb858433114cfaee00cbdbff2cf6f4755dbc4f4a1347e4701ff8e187626ffebe7989bf78e397af5e67ac297b843233553e0e8c59998b4fed43f6931ce7148608045728e75ff92cf8bca7fb23bbfd31ecc02833548e38056f2bdb433c97cb50bb6bae0b7e645db2c7c1a8139ccf6cb79e468be44ce60ff54dd2ac6080ed0196d441b608a31c21ac848e94f5f5702e27257895d6952b0c016ea1378401e2e0946e95138985e8eb1343d1071be96d5db7168d20e71b2109d66d8fbb83537817f688671de5623992e79342a4cf16acb2c47f1eb231a2ec86aa8226fe8eae73763f3d5056544830af5e4d4fbe869c91c54e8dc50aa4feed1ba230c0f388eec93c0d49c9362f1cc5782acd30327b1e6207f5cb25fcc0490e5a150aa5a75130a4e9af17929c0710c582a9901d074322f5e371a57c76a61a1d5107cdfd9db88ebb6f00f87f01c82ae3735cc0413709ab71f0be1455923fb249b535b49978e2a947e1087c87db246a485f62b51e207ef74530905252d7c2cf0227d26a64c54e58820da6ac4f5acd5aab65fbc782b56e4d0fe23e33e84c50d4d6ecaa750a0f9eb3e2b2c0401bc61dd0e4295ea6c8bb529633ce420424caba868a111c5435a148bb675874c01aed5eb66250e147e4f78b23b1421f7c6113f3d858dc42d6c7e9f48791476b61176281cb4d901634c7e3e1a6fc0aed4c1cdda8d96d0697fa4f910524e52f2965e6543dd4bf1384dcc5ea5525029f17a14d63d3a2cb490712a85c1fb7d5de3aee00865585eb9612b35c8033a959ee2aa5871c0a5012aa4b18aa4ff57edf4795129293fae067cada2b5b1b00c0a27d4f4912f6ba0cc68bf146b0f02e5624919c37b3feaa0721a2ad7ba8b68bcb6d9499ef68cc7af6b4c1e999f8788f097aa462f700867b96f44a79b4b96d73f8fcd0005f17e459ef4d9409063500fe3fd511e93963885ddb00d6406ec7991bf378993f7081a5491e0176f0afedb6dfa4c47523c6356f00e88001c82ae3735cc0413709ab71f0a81455927e5bdfab070ee7889378d521713234eb03ece970542dc06a18bfbff0807b3ab73a30b5cec83e3895584ca188fcd7409c998850b507c75ff7f8d763dacd55aa4e209c19a871b7120f6dbf9f701d167c357ad5d4030a289d0ed0096555f2f9c80f982184acd1d533667599906473985bde48e07bbab469209bf31380d04e13042566bf4a77b993b0336b4f1cf9f477db870469d66efa63c307936bd976a6c3178ff839e7245beac86290925fc67c32dfffddd1c6808fbb77410f9c2e3db1cd75746a5a50bdf8009390cb48eef42ef82b42dd68226224e138666549126fe3500ef7a8a874076b6c59de2bd3e64e0ff7d2a95d0587266a70a901c34d366e33a5998a4c8d073aeaeb36d3ea859748e4d3d95ec69e9c292e87e9ead6c0bfb320c497150424a5adf26f8cbcd82d5e8a987cf8d366aab06c9257e4372933de0c2627014c76dab2bcc4cfe90cffc7daa4718ace5da7733df2efa89b8e7dfaa3d0366dab6b8dc97051d1c3a56f311cc40008ecc6696f00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000, 'Eduardo', 'Pila', '0987152717', 'Ambato', 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`cedula`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;