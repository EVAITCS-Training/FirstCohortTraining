import { Route, Routes } from 'react-router-dom'
import './App.css'
import LandingPage from './static/LandingPage'
import Navbar from './static/Navbar'
import Register from './components/Register'
import Login from './components/Login'
import Dashboard from './components/Dashboard'

function App() {


  return (
    <>
      <Navbar/>
      <Routes>
        <Route element={<LandingPage/>} path='/' />
        <Route element={<Register/>} path='/register' />
        <Route element={<Login/>} path='/login'/>
        <Route element={<Dashboard/>} path='/dashboard' />
      </Routes>
    </>
  )
}

export default App
