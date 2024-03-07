package com.cpm;

class Tenant extends User {
    TenantAgreement currentAgreement;

    public Tenant(String username, String password, String name) {
        super(username, password, name);
    }

    public TenantAgreement getCurrentAgreement() {
        return currentAgreement;
    }

    public void setCurrentAgreement(TenantAgreement currentAgreement) {
        this.currentAgreement = currentAgreement;
    }
}
