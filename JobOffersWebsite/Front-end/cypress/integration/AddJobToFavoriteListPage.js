describe("renders the favorites jobs page", ()=>{
    beforeEach(() => {
        cy.login("test", "aass321456")
    })

    it("Add Job to list" , ()=>{
        cy.visit("/")
        cy.get(':nth-child(1) > .MuiPaper-root > .MuiCardActions-root > .MuiIconButton-root > [data-testid="FavoriteIcon"] > path').click();
        cy.get(':nth-child(2) > .nav-links').click();
        cy.get('[data-testid="VisibilityIcon"] > path').click();
        cy.get(':nth-child(2) > .nav-links').click();
    })

    it("delete Job from list" , ()=>{
        cy.visit("/favoriteList")
        cy.get('[data-testid="DeleteIcon"]').click();
    })
})