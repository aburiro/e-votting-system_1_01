module.exports = {
  networks: {
    development: {
      host: "127.0.0.1",     // Localhost (Ganache)
      port: 7545,            // Match Ganache GUI port
      network_id: "*"        // Match any network
    }
  },

  compilers: {
    solc: {
      version: "0.8.0",    // Match your contract pragma
    }
  }
};
