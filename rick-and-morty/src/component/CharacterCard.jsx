import React from "react";

class CharacterCard extends React.Component {

    render() {
        return(
            <div>
                <h3>
                    {this.props.name}
                </h3>
                <img src={this.props.img} alt={this.props.name} height="100" />
            </div>
        )
    }
}

export default CharacterCard