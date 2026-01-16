
const { defineConfig } = require("cypress");
const createBundler = require("@bahmutov/cypress-esbuild-preprocessor");
const {
  addCucumberPreprocessorPlugin,
} = require("@badeball/cypress-cucumber-preprocessor");
const {
  createEsbuildPlugin,
} = require("@badeball/cypress-cucumber-preprocessor/esbuild");

async function setupNodeEvents(on, config) {
  await addCucumberPreprocessorPlugin(on, config);

  on(
    "file:preprocessor",
    createBundler({
      plugins: [createEsbuildPlugin(config)],
    })
  );

  return config;
}

module.exports = defineConfig({
  e2e: {
    baseUrl: 'https://the-internet.herokuapp.com/',
    defaultCommandTimeout: 10000,
    viewportHeight: 960,
    viewportWidth: 1530,
    chromeWebSecurity: false,
    retries: {
      openMode: 1,
      runMode: 1
    },
    specPattern: '**/*.feature',
    setupNodeEvents,
  }
});
