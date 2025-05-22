import {Box, AppBar, Toolbar, Typography, Button} from "@mui/material";
import { Link } from "react-router-dom";


export default function Navbar() {
  return (
    <Box sx={{ flexGrow: 1 }}>
      <AppBar position="static">
        <Toolbar>
            <Link to="/">
                <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                    Home
                </Typography>
            </Link>
          <Button color="inherit">Login</Button>
        </Toolbar>
      </AppBar>
    </Box>
  )
}
