describe("renders the Add job page", ()=>{
    beforeEach(() => {
        cy.login("mo.sy06", "aass321456")
        // cy.visit('https://the-internet.herokuapp.com/upload')
    })

    it("Add Job" , ()=>{
        cy.visit("/addjob")
        const image = 'images/picture.jpg'
        cy.get('#jobName').clear();
        cy.get('#jobName').type('hallo');
        cy.get('#jobAddress').clear();
        cy.get('#jobAddress').type('hallo');
        cy.get(':nth-child(3) > input').attachFile(image)
        cy.get('#jobSalary').clear();
        cy.get('#jobSalary').type('344');
        cy.get('#jobDescription').type('3333');
        cy.get('select').select('"PartTime"');
        cy.get('button').click();
    })

    it("Edit Job" , ()=>{
        cy.login("mo.sy06", "aass321456")
        cy.visit("/")
        cy.get(':nth-child(3) > .nav-links').click();
        cy.get('[data-testid="EditIcon"] > path').click();
        cy.get('#jobName').clear();
        cy.get('#jobName').type('hallotest');
        cy.get('#jobAddress').clear();
        cy.get('#jobAddress').type('hallotest');
        cy.get('#jobSalary').clear();
        cy.get('#jobSalary').type('342');
        cy.get('#jobDescription').click();
        cy.get('select').select('"PartTime"');
        cy.get('button').click();
    })

    it("Delete Job" , ()=>{
        cy.login("mo.sy06", "aass321456")
        cy.visit("/")
        cy.get(':nth-child(3) > .nav-links').click();
        cy.get('[data-testid="DeleteIcon"] > path').click();
    })

    it("Add Job to test the other functionalities" , ()=>{
        cy.login("mo.sy06", "aass321456")
        cy.visit("/addjob")
        const image = 'images/picture.jpg'
        cy.get('#jobName').clear();
        cy.get('#jobName').type('hallo');
        cy.get('#jobAddress').clear();
        cy.get('#jobAddress').type('hallo');
        cy.get(':nth-child(3) > input').attachFile(image)
        cy.get('#jobSalary').clear();
        cy.get('#jobSalary').type('344');
        cy.get('#jobDescription').type('3333');
        cy.get('select').select('"PartTime"');
        cy.get('button').click();
    })
})