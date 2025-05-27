import { Route, Routes } from 'react-router-dom'
import './App.css'
import LandingPage from './static/LandingPage'
import Navbar from './static/Navbar'
import Register from './components/Register'
import Login from './components/Login'

function App() {


  return (
    <>
      <Navbar/>
      <Routes>
        <Route element={<LandingPage/>} path='/' />
        <Route element={<Register/>} path='/register' />
        <Route element={<Login/>} path='/login'/>
      </Routes>
    </>
  )
}

export default App
