package io.metropolis.rs.exceptions

class PermissionDeniedException(val permission: String) : RuntimeException()
