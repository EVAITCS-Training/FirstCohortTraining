// This script creates application users for MongoDB
db = db.getSiblingDB('admin');
// Authenticate as root user
db.auth(process.env.MONGO_INITDB_ROOT_USERNAME, process.env.MONGO_INITDB_ROOT_PASSWORD);

// Create app user in the bankapp database
db = db.getSiblingDB('bankapp');
db.createUser({
  user: process.env.MONGO_INITDB_APP_USERNAME,
  pwd: process.env.MONGO_INITDB_APP_PASSWORD,
  roles: [{ role: "readWrite", db: "bankapp" }]
});

// Create admin user if needed
db = db.getSiblingDB('admin');
db.createUser({
  user: process.env.MONGO_INITDB_ADMIN_USERNAME,
  pwd: process.env.MONGO_INITDB_ADMIN_PASSWORD,
  roles: [{ role: "userAdminAnyDatabase", db: "admin" }]
});