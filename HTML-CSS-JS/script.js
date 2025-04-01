// function characterClassSelector() {

// }

// const characterClassSelector = () => {

// }

var humanExample
var dateExample
var numberExample

const characterClassSelector = function(e) {
    e.preventDefault();
    e.preventDefault();
    let selectElement = document.getElementById("character-class");
    let characterClass = selectElement.value;
    
    let characterData;
    
    switch(characterClass) {
        case "paladin":
            characterData = {
                str: 18,
                dex: 14,
                chr: 16,
                con: 17,
                hp: 24,
                // Store function names instead of actual functions
                abilities: ["attack", "defend", "heal"]
            };
            
            // Convert to JSON and store in a custom attribute
            selectElement.setAttribute('data-character', JSON.stringify(characterData));
            
            // If you want to display the data somewhere
            document.getElementById('character-display').textContent = JSON.stringify(characterData, null, 2);
            break;
            
        default:
            alert("Invalid character selection");
            return;
    }
}

function submitForm(data) {
    e.preventDefault()
    humanExample = 10
    alert(data)
}

const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
};

const listBackgroundChanger = () => {
    humanExample = 20
    const lists = document.getElementsByTagName("ol");
    Array.from(lists).forEach(element => {
        element.style.backgroundColor = getRandomColor();
    });
};

Array.from(document.getElementsByTagName("ol")).forEach(element => {
    humanExample = 30
    element.addEventListener("click", () => listBackgroundChanger());
});

let letVarible;

const PI = 3.14;

var varExample = true;

console.log(hoistExample)
var hoistExample = 20
console.log(hoistExample)

const fetchCall = async () => {
    const request = await fetch("https://dummyjson.com/posts")
    .then(res => {
        console.log(res)
    })
    .then(data => {

    }).catch(err => {
        console.error(err)
    })
}

fetchCall()