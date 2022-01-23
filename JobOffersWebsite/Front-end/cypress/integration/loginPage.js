describe("renders the login page", ()=>{
    it("Login successfully" , ()=>{
        cy.visit("/login")
        cy.get(':nth-child(2) > .form-control').clear();
        cy.get(':nth-child(2) > .form-control').type('Mo@gmail.com');
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('Mo');
        cy.get('.btn').click();
    })

    it("Logout successfully" , ()=>{
        cy.visit("/login")
        cy.get(':nth-child(2) > .form-control').clear();
        cy.get(':nth-child(2) > .form-control').type('Mo@gmail.com');
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('Mo');
        cy.get('.btn').click();
        cy.get(':nth-child(6) > .nav-links').click();
    })
})