insert into permissions (name,description) values ("Obtener_Usuarios","Permite Obtener a todos los usuarios del sistema"),
                               ("Obtener_un_Usuario","Permite obtener la Informacion de un usuario"),
                               ("Buscar_Usuario_por_Nombre","Permite buscar a todos los usuarios que coincidan con un nombre especifico"),
                               ("Buscar_Usuario_por_Apellido","Permite buscar a todos los usuarios que coincidan con un Apellido especifico"),
                               ("Crear_Usuario","Permite insertar datos para crear un nuevo usuario"),
                               ("Actualizar_Usuario","Permite editar la informacion de un usuario"),
                               ("Desactivar_Usuario","Permite desactivar un usuario del sistema"),
                               ("Obtener_Roles","Permite obtener a todos los roles del sistema"),
                               ("Obtener_un_Rol","Permite obtener la informacion de un rol"),
                               ("Buscar_Rol_por_Nombre","Permite buscar roles por nombre especifico"),
                               ("Crear_Rol","Permite crear un nuevo rol"),
                               ("Actualizar_Rol","Permite editar la informacion de un rol"),
                               ("Desactivar_Rol","Permite desactivar un rol del sistema"),
                               ("Obtener_Permisos","Permite obtener a todos los permisos del sistema"),
                               ("Obtener_un_Permiso","Permite obtener la informacion de un permiso"),
                               ("Buscar_Permiso_por_Nombre","Permite buscar permisos por nombre especifico"),
                               ("Buscar_Permiso_por_Descripcion","Permite buscar permisos por descripcion"),
                               ("Actualizar_Permiso","Permite editar la informacion de un permiso"),
                               ("Obtener_Permisos_Rol","Permite obtener los permisos asignados a un rol"),
                               ("Obtener_Permiso_Rol","Permite obtener la informacion de un permiso de rol"),
                               ("Buscar_Permisos_por_Rol","Permite buscar permisos por rol especifico"),
                               ("Buscar_Permisos_Role_por_ID","Permite buscar permisosRole por un id especifico"),
                               ("Crear_Permiso_Rol","Permite asignar un permiso a un rol"),
                               ("Eliminar_Permiso_Rol","Permite eliminar un permiso de un rol"),
                               ("Obtener_Campos_Personalizados","Permite obtener a todos los campos personalizados"),
                               ("Obtener_un_Campo_Personalizado","Permite obtener la informacion de un campo personalizado"),
                               ("Crear_Campo_Personalizado","Permite crear un nuevo campo personalizado"),
                               ("Actualizar_Campo_Personalizado","Permite editar la informacion de un campo personalizado"),
                               ("Desactivar_Campo_Personalizado","Permite desactivar un campo personalizado"),
                               ("Obtener_Reportes","Permite obtener a todos los reportes"),
                               ("Obtener_un_Reporte","Permite obtener la informacion de un reporte"),
                               ("Crear_Reporte","Permite crear un nuevo reporte"),
                               ("Actualizar_Reporte","Permite editar la informacion de un reporte"),
                               ("Desactivar_Reporte","Permite desactivar un reporte"),
                               ("Obtener_Categorias_Reporte","Permite obtener a todas las categorias de reporte"),
                               ("Obtener_una_Categoria_Reporte","Permite obtener la informacion de una categoria de reporte"),
                               ("Crear_Categoria_Reporte","Permite crear una nueva categoria de reporte"),
                               ("Actualizar_Categoria_Reporte","Permite editar la informacion de una categoria de reporte"),
                               ("Desactivar_Categoria_Reporte","Permite desactivar una categoria de reporte"),
                               ("Obtener_Campos_Reporte","Permite obtener a todos los campos de reporte"),
                               ("Obtener_un_Campo_Reporte","Permite obtener la informacion de un campo de reporte"),
                               ("Crear_Campo_Reporte","Permite crear un nuevo campo de reporte"),
                               ("Actualizar_Campo_Reporte","Permite editar la informacion de un campo de reporte"),
                               ("Desactivar_Campo_Reporte","Permite desactivar un campo de reporte"),
                               ("Obtener_Valores_Campo_Reporte","Permite obtener a todos los valores de campos de reporte"),
                               ("Obtener_Valor_Campo_Reporte","Permite obtener la informacion de un valor de campo de reporte"),
                               ("Crear_Valor_Campo_Reporte","Permite crear un nuevo valor de campo de reporte"),
                               ("Actualizar_Valor_Campo_Reporte","Permite editar la informacion de un valor de campo de reporte"),
                               ("Desactivar_Valor_Campo_Reporte","Permite desactivar un valor de campo de reporte"),
                               ("Subir_Archivo_Valor_Campo_Reporte","Permite subir archivos a valores de campos de reporte"),
                               ("Obtener_Servicios","Permite obtener a todos los servicios"),
                               ("Obtener_un_Servicio","Permite obtener la informacion de un servicio"),
                               ("Crear_Servicio","Permite crear un nuevo servicio"),
                               ("Actualizar_Servicio","Permite editar la informacion de un servicio"),
                               ("Desactivar_Servicio","Permite desactivar un servicio"),
                               ("Obtener_Campos_Personalizados_Servicio","Permite obtener los campos personalizados de un servicio"),
                               ("Crear_Campo_Personalizado_Servicio","Permite crear un campo personalizado para un servicio"),
                               ("Desactivar_Campo_Personalizado_Servicio","Permite desactivar un campo personalizado de un servicio"),
                               ("Obtener_Tipos","Permite obtener a todos los tipos"),
                               ("Obtener_un_Tipo","Permite obtener la informacion de un tipo"),
                               ("Buscar_Tipo_por_Nombre","Permite buscar tipos por nombre especifico"),
                               ("Crear_Tipo","Permite crear un nuevo tipo"),
                               ("Actualizar_Tipo","Permite editar la informacion de un tipo"),
                               ("Desactivar_Tipo","Permite desactivar un tipo");


INSERT INTO roles (name, description, active) VALUES
("Admin","Rol con todos los permisos del sistema", true);

Insert into Roles_Permisos ( role_id,permission_id) values (1,1),
                        (1,2),
                        (1,3),
                        (1,4),
                        (1,5),
                        (1,6),
                        (1,7),
                        (1,8),
                        (1,9),
                        (1,10),
                        (1,11),
                        (1,12),
                        (1,13),
                        (1,14),
                        (1,15),
                        (1,16),
                        (1,17),
                        (1,18),
                        (1,19),
                        (1,20),
                        (1,21),
                        (1,22),
                        (1,23),
                        (1,24),
                        (1,25),
                        (1,26),
                        (1,27),
                        (1,28),
                        (1,29),
                        (1,30),
                        (1,31),
                        (1,32),
                        (1,33),
                        (1,34),
                        (1,35),
                        (1,36),
                        (1,37),
                        (1,38),
                        (1,39),
                        (1,40),
                        (1,41),
                        (1,42),
                        (1,43),
                        (1,44),
                        (1,45),
                        (1,46),
                        (1,47),
                        (1,48),
                        (1,49),
                        (1,50),
                        (1,51),
                        (1,52),
                        (1,53),
                        (1,54),
                        (1,55),
                        (1,56),
                        (1,57),
                        (1,58),
                        (1,59),
                        (1,60),
                        (1,61),
                        (1,62),
                        (1,63),
                        (1,64);

-- Usuario Admin
INSERT INTO users (name, suername, email, password, isActive, role_id, phoneNumber, perosnalId, address, photoProfileUrl) VALUES
("Admin", "Sistema", "admin@despachoCastillo.com", "admin123", true, 1, "+506-0000-0000", "0-0000-0000", "Despacho Castillo y Asociados", NULL);

-- =====================================================
-- TRIGGERS PARA PROTEGER AL USUARIO ADMIN Y ROL ADMIN
-- =====================================================

-- Trigger 1: Evitar que se cambie el rol del usuario admin
DELIMITER $$

CREATE TRIGGER prevent_admin_user_role_change
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.id = 1 AND OLD.role_id != NEW.role_id THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede cambiar el rol del usuario administrador del sistema';
    END IF;
END$$

DELIMITER ;

-- Trigger 2: Evitar que se desactive el usuario admin
DELIMITER $$

CREATE TRIGGER prevent_admin_user_deactivation
BEFORE UPDATE ON users
FOR EACH ROW
BEGIN
    IF OLD.id = 1 AND OLD.isActive = true AND NEW.isActive = false THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede desactivar el usuario administrador del sistema';
    END IF;
END$$

DELIMITER ;

-- Trigger 3: Evitar que se desactive el rol admin
DELIMITER $$

CREATE TRIGGER prevent_admin_role_deactivation
BEFORE UPDATE ON roles
FOR EACH ROW
BEGIN
    IF OLD.id = 1 AND OLD.active = true AND NEW.active = false THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede desactivar el rol administrador del sistema';
    END IF;
END$$

DELIMITER ;

-- Trigger 4: Evitar que se eliminen permisos del rol admin
DELIMITER $$

CREATE TRIGGER prevent_admin_role_permission_delete
BEFORE DELETE ON Roles_Permisos
FOR EACH ROW
BEGIN
    IF OLD.role_id = 1 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden eliminar los permisos del rol administrador del sistema';
    END IF;
END$$

DELIMITER ;

-- Trigger 5: Evitar que se modifique la asignación de permisos del rol admin
DELIMITER $$

CREATE TRIGGER prevent_admin_role_permission_update
BEFORE UPDATE ON Roles_Permisos
FOR EACH ROW
BEGIN
    IF OLD.role_id = 1 OR NEW.role_id = 1 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se pueden modificar los permisos del rol administrador del sistema';
    END IF;
END$$

DELIMITER ;

-- Trigger 6: Evitar que se elimine el rol admin
DELIMITER $$

CREATE TRIGGER prevent_admin_role_delete
BEFORE DELETE ON roles
FOR EACH ROW
BEGIN
    IF OLD.id = 1 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede eliminar el rol administrador del sistema';
    END IF;
END$$

DELIMITER ;






