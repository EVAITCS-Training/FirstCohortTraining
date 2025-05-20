import React from "react";
import CharacterCard from "./CharacterCard";

class CharacterIndex extends React.Component {
    constructor() {
        super()
        this.state = {
            characters: []
        }
    }

    componentDidMount() {
        this.fetchData()
    }

    componentDidUpdate() {
    }

    componentWillUnmount() {
        //this.setState({})
    }

    async fetchData() {
        const request = await fetch("https://rickandmortyapi.com/api/character")
        const response = await request.json()
            .then(data => {
                this.setState({ characters: data.results })
            })
            .catch(err => alert("Error has occurred: " + err.message))
        console.log(this.state)
    }

    render() {
        return (
            <>
            <h1>Hello from Character index</h1>
            <div>
                {
                    this.state.characters.map(char => {
                        return(
                            <CharacterCard name={char.name} img={char.image} />
                        )
                    })
                }
            </div>
            </>
        )
    }
}

export default CharacterIndex