import { Box, Container, List, ListItem, Typography } from "@mui/material"

export default function LandingPage(): React.ReactElement {
  return (
    <Container maxWidth="md">
        <Box component="section" sx={{maxWidth: "100%", backgroundColor: "cornflowerblue", display: "grid", gridTemplateColumns: "repeat(3, 18rem)", margin: "3rem auto", padding: "3rem 1rem"}}>
            <Box></Box>
            <Typography variant="h1" sx={{fontSize: "2rem", margin: "0 2rem"}}>
                Welcome to Bank App
            </Typography>
            <Box></Box>
        </Box>
        <br />
        <Box component="section" sx={{maxWidth: "100%", height: "15%", backgroundColor: "lightgray"}}>
            <List>
                <ListItem>
                    <Typography variant="p">
                        Providing excellent security for your money
                    </Typography>
                </ListItem>
                <ListItem>
                    <Typography variant="p">
                        Best interest rate for savings across the country
                    </Typography>
                </ListItem>
            </List>
        </Box>
    </Container>
  )
}
