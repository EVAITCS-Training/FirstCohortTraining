// App.jsx
// This is the main entry point for the React application.
// It imports the CharacterIndex component and renders it inside the app.
// The App component is a functional component, which is a simpler way to write components that only need to render UI.

import './App.css' // Import the CSS file for styling the app
import CharacterIndex from './component/CharacterIndex' // Import the CharacterIndex component

function App() {
  // The return statement defines the UI for the App component using JSX.
  return (
    <>
      {/* Render the CharacterIndex component, which will display the list of characters */}
      <CharacterIndex/>
    </>
  )
}

// Export the App component so it can be used by the main entry file (main.jsx)
export default App;
