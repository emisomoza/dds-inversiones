package ar.edu.utn.dds.permisos.evaluadores

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication

class CustomPermissionEvaluator implements PermissionEvaluator {

    @Override
    boolean hasPermission(Authentication authentication, Object o, Object o1) {
        return false
    }

    @Override
    boolean hasPermission(Authentication authentication, Serializable serializable, String s, Object o) {
        return false
    }
}
