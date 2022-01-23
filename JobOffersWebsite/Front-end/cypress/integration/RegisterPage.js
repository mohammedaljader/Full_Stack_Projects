describe("renders the the register page", ()=>{
    it("Register succesfully" , ()=>{
        cy.visit("/")
        cy.get(':nth-child(1) > .nav-links').click();
        cy.get(':nth-child(2) > .form-control').clear();
        cy.get(':nth-child(2) > .form-control').type('Mohammed');
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('mo.sy06');
        cy.get(':nth-child(4) > .form-control').clear();
        cy.get(':nth-child(4) > .form-control').type('mo');
        cy.get(':nth-child(5) > .form-control').clear();
        cy.get(':nth-child(5) > .form-control').type('mohammedjader@gmail.com');
        cy.get(':nth-child(6) > .form-control').clear();
        cy.get(':nth-child(6) > .form-control').type('Geldrop');
        cy.get('.btn').click();
        cy.get(':nth-child(2) > .form-control').clear();
        cy.get(':nth-child(2) > .form-control').type('mo.sy06');
        cy.get(':nth-child(3) > .form-control').clear();
        cy.get(':nth-child(3) > .form-control').type('mo');
        cy.get('.btn').click();
    })


})