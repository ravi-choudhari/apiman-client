package org.apiman.client.domain.audit;

public enum AuditEntryType {

	// Entity events
    Create, Update, Delete, Clone, 
    // Action events
    Grant, Revoke,
    Publish, Retire,
    Register, Unregister,
    AddPolicy, RemovePolicy, UpdatePolicy, ReorderPolicies,
    CreateContract, BreakContract,
    Lock,
    UpdateDefinition, DeleteDefinition
}
