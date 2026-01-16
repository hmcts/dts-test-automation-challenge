let data
import { Given, When, Then, Before } from '@badeball/cypress-cucumber-preprocessor';

Before(() => {
    cy.fixture('selectors.json').then((selectors) => {
        data = selectors;
    });
});

Given(/^I navigate to "([^"]*)" page$/, (path) => {
	cy.visit(path);
});

Then(/^I should see the following on the login page$/, (datatable) => {
	datatable.hashes().forEach(row => {
        cy.get(data.HomePageText).should('contain.text', row.text);
    })
});

When(/^I enter username as "([^"]*)"$/, (username) => {
	cy.get(data.usernameField).should('be.visible').type(username);
});

When(/^I enter password as "([^"]*)"$/, (password) => {
	cy.get(data.passwordField).should('be.visible').type(password);
});

When(/^I click on "([^"]*)" (button|link)$/, (text, element) => {
	switch(element) {
        case 'button':
            cy.get(data.button).contains(text).should('be.visible').click();
            break;
        case 'link':
            cy.get(data.link).contains(text).should('be.visible').click();
            break;
        default:
            throw new Error(`Element with text:${element} not found`);
    };
});

Then(/^I should see the  "([^"]*)" message$/, (message) => {
        cy.verifyAccessMessage(message);
});

            