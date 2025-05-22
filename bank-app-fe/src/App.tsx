import { Route, Routes } from 'react-router-dom'
import './App.css'
import LandingPage from './static/LandingPage'
import Navbar from './static/Navbar'

function App() {


  return (
    <>
      <Navbar/>
      <Routes>
        <Route element={<LandingPage/>} path='/' />
      </Routes>
    </>
  )
}

export default App
