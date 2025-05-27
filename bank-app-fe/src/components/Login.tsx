import { useMutation } from "@tanstack/react-query"
import axios from "axios"
import { useFormik } from "formik"
import { useState } from "react"
import { useNavigate } from "react-router-dom"
import * as yup from 'yup'
import type UserData from "../types/UserData"
import { PersonAdd, VisibilityOff, Visibility } from "@mui/icons-material"
import { Container, Paper, Box, Typography, Alert, Grid, TextField, InputAdornment, IconButton, Button, CircularProgress } from "@mui/material"


export default function Login() {
    const navigate = useNavigate()
    const [showPassword, setShowPassword] = useState(false)

    const validationSchema = yup.object({
        username: yup.string().required("Username is required for logging in"),
        password: yup.string().required("Password is required")
    })

    const { mutate, isPending, error }: typeof useMutation = useMutation({
        mutationKey: ["auth"],
        mutationFn: async (userData: UserData): Promise<string> => {
            const request = await axios.post(import.meta.env.VITE_API_URL + "/auth/login", {
                username: userData.username,
                password: userData.password
            },
        {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            const response = request
            console.log(response)
            return response.data.token
        },
        onSuccess: (data) => {
            alert("User successfully logged in")
            sessionStorage.setItem("Authorization", data)
            navigate("/dashboard")
        },
        onError: err => {
            console.error("Logging error:", err)
        }
    })

    const formik: typeof useFormik = useFormik({
            initialValues: {
                username: "",
                password: ""
            },
            validationSchema: validationSchema,
            onSubmit: mutate,
        })

    const handleTogglePasswordVisibility = () => {
        setShowPassword(prev => !prev)
    }

  return (
    <Container maxWidth="md" sx={{ py: 4 }}>
            <Paper elevation={3} sx={{ p: 4 }}>
                <Box sx={{ mb: 3, textAlign: 'center' }}>
                    <PersonAdd sx={{ fontSize: 48, color: 'primary.main', mb: 2 }} />
                    <Typography variant="h4" component="h1" gutterBottom>
                        Log In
                    </Typography>
                    <Typography variant="body1" color="text.secondary">
                        Access your account and get started with your banking experience
                    </Typography>
                </Box>

                {error && (
                    <Alert severity="error" sx={{ mb: 3 }}>
                        {error.message || "An error occurred during registration"}
                    </Alert>
                )}

                <Box component="form" onSubmit={formik.handleSubmit} noValidate>
                    <Grid container spacing={3}>
                        {/* Account Information Section */}
                        <Grid item xs={12}>
                            <Typography variant="h6" gutterBottom sx={{ borderBottom: 1, borderColor: 'divider', pb: 1 }}>
                                Account Information
                            </Typography>
                        </Grid>
                        
                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                id="username"
                                name="username"
                                label="Username"
                                value={formik.values.username}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.username && Boolean(formik.errors.username)}
                                helperText={formik.touched.username && formik.errors.username}
                                disabled={isPending}
                                autoComplete="username"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                id="password"
                                name="password"
                                label="Password"
                                type={showPassword ? 'text' : 'password'}
                                value={formik.values.password}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.password && Boolean(formik.errors.password)}
                                helperText={formik.touched.password && formik.errors.password}
                                disabled={isPending}
                                autoComplete="new-password"
                                InputProps={{
                                    endAdornment: (
                                        <InputAdornment position="end">
                                            <IconButton
                                                aria-label="toggle password visibility"
                                                onClick={handleTogglePasswordVisibility}
                                                edge="end"
                                                disabled={isPending}
                                            >
                                                {showPassword ? <VisibilityOff /> : <Visibility />}
                                            </IconButton>
                                        </InputAdornment>
                                    ),
                                }}
                            />
                        </Grid>
                    </Grid>

                    {/* Submit Button */}
                    <Box sx={{ mt: 4, display: 'flex', justifyContent: 'center' }}>
                        <Button
                            type="submit"
                            variant="contained"
                            size="large"
                            disabled={isPending || !formik.isValid}
                            startIcon={isPending ? <CircularProgress size={20} /> : <PersonAdd />}
                            sx={{ minWidth: 200, py: 1.5 }}
                        >
                            {isPending ? 'Logging in Account...' : 'Log in Account'}
                        </Button>
                    </Box>

                    {/* Login Link */}
                    <Box sx={{ mt: 3, textAlign: 'center' }}>
                        <Typography variant="body2" color="text.secondary">
                            Need to create account?{' '}
                            <Button 
                                variant="text" 
                                onClick={() => navigate('/register')}
                                disabled={isPending}
                                sx={{ textTransform: 'none' }}
                            >
                                Sign up here
                            </Button>
                        </Typography>
                    </Box>
                </Box>
            </Paper>
        </Container>
  )
}
