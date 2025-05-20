// CharacterCard.jsx
// This component is responsible for displaying a single character's information.
// It receives props from its parent (CharacterIndex) and renders the character's name and image.

import React from "react";

class CharacterCard extends React.Component {
    // The render method returns the JSX to display the character's card.
    render() {
        return(
            <div>
                {/* Display the character's name in a heading */}
                <h3>
                    {this.props.name}
                </h3>
                {/* Display the character's image with alt text for accessibility */}
                <img src={this.props.img} alt={this.props.name} height="100" />
            </div>
        )
    }
}

// Export the CharacterCard component so it can be used in other parts of the app
export default CharacterCard;

