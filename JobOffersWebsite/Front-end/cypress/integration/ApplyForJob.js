describe("renders the Job Application Job", ()=>{
    beforeEach(() => {
        cy.login("test", "aass321456")
    })

    it("Apply for job" , ()=>{
        cy.visit("/")
        const file = 'images/cv.pdf'
        cy.get('[aria-label="Apply for job"] > a').click();
        cy.get(':nth-child(1) > input').clear();
        cy.get(':nth-child(1) > input').type('I love this job');
        cy.get(':nth-child(2) > input').attachFile(file)
        cy.get('button').click();
    })

    // it("Remove job application" , ()=>{
    //     cy.visit('/');
    //     const file = 'images/cv.pdf'
    //     cy.get('[aria-label="Apply for job"] > a').click();
    //     cy.get(':nth-child(1) > input').clear();
    //     cy.get(':nth-child(1) > input').type('I love this jobbbbb');
    //     cy.get(':nth-child(2) > input').attachFile(file)
    //     cy.get('button').click();
    //     cy.get(':nth-child(4) > .nav-links').click();
    //     cy.get(':nth-child(1) > :nth-child(5) > [data-cy="delete"] > [data-testid="DeleteIcon"] > path').click();
    // })
})
