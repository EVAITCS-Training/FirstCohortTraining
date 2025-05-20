// main.jsx
// This file is the true entry point for the React application.
// It is responsible for rendering the root App component into the DOM.
// It uses React's StrictMode to help find potential problems in the application.

import { StrictMode } from 'react' // Import StrictMode for highlighting potential problems
import { createRoot } from 'react-dom/client' // Import createRoot for modern React rendering
import './index.css' // Import global CSS styles
import App from './App.jsx' // Import the main App component

// Find the root DOM node (with id 'root') and render the App component inside it.
// The <StrictMode> wrapper is a tool for highlighting potential problems in development.
createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
