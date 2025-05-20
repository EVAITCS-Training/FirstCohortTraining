// CharacterIndex.jsx
// This component fetches and displays a list of characters from the Rick and Morty API.
// It uses a class-based React component to manage state and lifecycle methods.

import React from "react";
import CharacterCard from "./CharacterCard"; // Import the CharacterCard component to display each character

class CharacterIndex extends React.Component {
    // The constructor initializes the component's state.
    constructor() {
        super(); // Call the parent class (React.Component) constructor
        this.state = {
            characters: [] // Initialize an empty array to hold character data
        };
    }

    // componentDidMount is a React lifecycle method that runs after the component is first rendered.
    componentDidMount() {
        this.fetchData(); // Fetch character data from the API when the component mounts
    }

    // componentDidUpdate is another lifecycle method, but it's not used here.
    componentDidUpdate() {
        // This could be used to respond to state or prop changes
    }

    // componentWillUnmount is called right before the component is removed from the DOM.
    componentWillUnmount() {
        // Cleanup could be performed here if needed
        // this.setState({}) // Not needed in this case
    }

    // fetchData is an asynchronous method to fetch character data from the API.
    async fetchData() {
        // Fetch data from the Rick and Morty API endpoint
        const request = await fetch("https://rickandmortyapi.com/api/character");
        // Parse the response as JSON and update the state with the results
        const response = await request.json()
            .then(data => {
                // Set the characters state to the array of character objects from the API
                this.setState({ characters: data.results });
            })
            .catch(err => alert("Error has occurred: " + err.message)); // Handle errors
        // Log the current state to the console (for debugging)
        console.log(this.state);
    }

    // The render method returns the JSX to display the component's UI.
    render() {
        return (
            <>
            {/* Heading for the character index */}
            <h1>Hello from Character index</h1>
            <div>
                {/* Map over the characters array in state and render a CharacterCard for each character */}
                {
                    this.state.characters.map(char => {
                        return(
                            // Pass the character's name and image as props to CharacterCard
                            <CharacterCard name={char.name} img={char.image} />
                        );
                    })
                }
            </div>
            </>
        );
    }
}

// Export the CharacterIndex component so it can be used in other parts of the app
export default CharacterIndex;

