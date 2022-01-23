describe("renders the home page", ()=>{  
    // beforeEach(() => {
    //     cy.task('db:seed')
    //     cy.login("Mo@gmail.com", "Mo")
    // })
    it("renders correctly", ()=>{
        cy.visit("/")
        cy.get("#jobs").should("exist")
    })

    it("View button should login", ()=>{
        cy.visit('/');
        cy.get(':nth-child(1) > .MuiPaper-root > .MuiCardActions-root > [aria-label="View job"] > a').click();
    })

    it("Apply button should login", ()=>{
        cy.visit('/');
        cy.get(':nth-child(1) > .MuiPaper-root > .MuiCardActions-root > [aria-label="Apply for job"] > a').click();
    })

    it("add to favorite list button should login", ()=>{
        cy.visit('http://localhost:3000/');
        cy.get('[data-testid="FavoriteIcon"] > path').click();
    })

    it("Filter the jobs", ()=>{
        cy.visit('/');
        // cy.get('body').click();
        // cy.get('#select').should("exist");

        cy.get('body').click();
        cy.get('#all').should("exist");
        cy.get('body').click();
        cy.get('#PartTime').should("exist");
        // cy.get('#select').eq(0).type('{downarrow}{downarrow}{downarrow}{enter}')
        // cy.get('#select').eq(1).type('{downarrow}{downarrow}{downarrow}{enter}')
        // cy.get('#select').eq(2).type('{downarrow}{downarrow}{downarrow}{enter}')
    })
})