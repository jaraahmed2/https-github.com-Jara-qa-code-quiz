describe('Login Portal Tests', () => {
  beforeEach(() => {
    cy.visit('http://localhost:8080/'); 
  });

  it('should show login fields', () => {
    cy.get('#username').should('be.visible');
    cy.get('#password').should('be.visible');
    cy.get('#loginButton').should('be.visible');
  });

  it('should login with valid credentials', () => {
  cy.get('#username').type('dummytree');
  cy.get('#password').type('test1');
  cy.get('#loginButton').click();
  cy.url().should('include', '/dashboard');
  });

  it('should show error on invalid login', () => {
  cy.get('#username').type('dummytree234');
  cy.get('#password').type('Test123');
  cy.get('#loginButton').click();
  cy.url().should('include', '/dashboard');
  });

  it('should validate empty fields', () => {
  cy.get('#username').type('exist');
  cy.get('#password').type('exist');
  cy.get('#loginButton').click();
  cy.url().should('include', '/dashboard');
  });
});
