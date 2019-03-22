package eu.antoniolopez.playground.exceptions

class PermissionDeniedException(val permission: String) : RuntimeException()
