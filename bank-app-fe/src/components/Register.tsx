import * as yup from 'yup'
import axios from 'axios'
import { useFormik } from 'formik'
import { useMutation } from '@tanstack/react-query'
import type UserData from '../types/UserData'
import type UserProfile from '../types/UserProfile'
import { useNavigate } from 'react-router-dom'
import { 
  Container, 
  Paper, 
  TextField, 
  Button, 
  Typography, 
  Box, 
  Grid, 
  Alert,
  CircularProgress,
  InputAdornment,
  IconButton
} from '@mui/material'
import { Visibility, VisibilityOff, PersonAdd } from '@mui/icons-material'
import { useState } from 'react'

export default function Register() {
    const navigate = useNavigate()
    const [showPassword, setShowPassword] = useState(false)

    const validationSchema = yup.object({
        username: yup.string().required("Username is required for registration")
        .min(3, "You have to have a min of three characters").max(150, "You cannot create a username greater than 150 characters"),
        password: yup.string().required("Password is required").min(8, "password has to be min of 8 characters").matches(
            /^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*(),.?\\":{}|<>]).{8,}$/,
    "Password must contain at least 8 characters, one uppercase, one lowercase, and one symbol"
        ),
        firstName: yup.string().required("First Name required when registering with the bank").min(3, "Need a min of 3 characters"),
        lastName: yup.string().required("Last Name required when registering with the bank").min(3, "Need a min of 3 characters"),
        phoneNumber: yup.string().required("Phone number required when registering with bank").length(10, "Has to be 10 characters long").matches(
            /^(\+?1[-.\s]?)?\(?([0-9]{3})\)?[-.\s]?([0-9]{3})[-.\s]?([0-9]{4})$/,
    "Please enter a valid phone number"
        ),
        streetAddress: yup.string()
  .required("Street address is required")
  .min(5, "Address must be at least 5 characters")
  .max(100, "Address must be less than 100 characters")
  .matches(/^[0-9]+[A-Za-z]?[\s\-]?[A-Za-z0-9\s\-\.,'#]+$/, "Invalid street address format"),
        city: yup.string().required("Bank needs city you live at"),
        state: yup.string().required("State is required"),
        zipCode: yup.string().required("Zipcode is required")
    })

    const { mutate, isPending, error }: typeof useMutation = useMutation({
        mutationKey: ["userProfile"],
        mutationFn: async (userData: UserData): Promise<UserProfile> => {
            const request = await axios.post(import.meta.env.VITE_API_URL + "/auth/register", {
                username: userData.username,
                password: userData.password
            },
        {
            headers: {
                'Content-Type': 'application/json'
            }
        })
            const response = request

            if(response.status === 200){
                const request2 = await axios.post(import.meta.env.VITE_API_URL + "/user-profile/", {
                    userCredentialId: response.data,
                    firstName: userData.firstName,
                    lastName: userData.lastName,
                    phoneNumber: userData.phoneNumber,
                    streetAddress: userData.streetAddress,
                    city: userData.city,
                    zipCode: userData.zipCode,
                    state: userData.state
                },
                {
                    headers: {
                    'Content-Type': 'application/json'
                    }
                })

                const response2 = request2
                if(response2.status === 201) {
                    const results: UserProfile = response2.data
                    return results
                } else {
                    return Promise.reject(new Error("Something has occured while saving the user profile"))
                }
            } else {
                return Promise.reject(new Error("Something has occured while saving the user credentials"))
            }

        },
        onSuccess: () => {
            alert("User successfully saved")
            navigate("/login")
        },
        onError: err => {
            console.error("Registration error:", err)
        }
    })

    const formik: typeof useFormik = useFormik({
        initialValues: {
            username: "",
            password: "",
            firstName: "",
            lastName: "",
            phoneNumber: "",
            streetAddress: "",
            city: "",
            state: "",
            zipCode: ""
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
                        Create Your Account
                    </Typography>
                    <Typography variant="body1" color="text.secondary">
                        Join us today and get started with your banking experience
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

                        {/* Personal Information Section */}
                        <Grid item xs={12}>
                            <Typography variant="h6" gutterBottom sx={{ borderBottom: 1, borderColor: 'divider', pb: 1, mt: 2 }}>
                                Personal Information
                            </Typography>
                        </Grid>

                        <Grid item xs={12} sm={6}>
                            <TextField
                                fullWidth
                                id="firstName"
                                name="firstName"
                                label="First Name"
                                value={formik.values.firstName}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.firstName && Boolean(formik.errors.firstName)}
                                helperText={formik.touched.firstName && formik.errors.firstName}
                                disabled={isPending}
                                autoComplete="given-name"
                            />
                        </Grid>

                        <Grid item xs={12} sm={6}>
                            <TextField
                                fullWidth
                                id="lastName"
                                name="lastName"
                                label="Last Name"
                                value={formik.values.lastName}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.lastName && Boolean(formik.errors.lastName)}
                                helperText={formik.touched.lastName && formik.errors.lastName}
                                disabled={isPending}
                                autoComplete="family-name"
                            />
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                id="phoneNumber"
                                name="phoneNumber"
                                label="Phone Number"
                                placeholder="1234567890"
                                value={formik.values.phoneNumber}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.phoneNumber && Boolean(formik.errors.phoneNumber)}
                                helperText={formik.touched.phoneNumber && formik.errors.phoneNumber}
                                disabled={isPending}
                                autoComplete="tel"
                            />
                        </Grid>

                        {/* Address Information Section */}
                        <Grid item xs={12}>
                            <Typography variant="h6" gutterBottom sx={{ borderBottom: 1, borderColor: 'divider', pb: 1, mt: 2 }}>
                                Address Information
                            </Typography>
                        </Grid>

                        <Grid item xs={12}>
                            <TextField
                                fullWidth
                                id="streetAddress"
                                name="streetAddress"
                                label="Street Address"
                                placeholder="123 Main St"
                                value={formik.values.streetAddress}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.streetAddress && Boolean(formik.errors.streetAddress)}
                                helperText={formik.touched.streetAddress && formik.errors.streetAddress}
                                disabled={isPending}
                                autoComplete="street-address"
                            />
                        </Grid>

                        <Grid item xs={12} sm={6}>
                            <TextField
                                fullWidth
                                id="city"
                                name="city"
                                label="City"
                                value={formik.values.city}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.city && Boolean(formik.errors.city)}
                                helperText={formik.touched.city && formik.errors.city}
                                disabled={isPending}
                                autoComplete="address-level2"
                            />
                        </Grid>

                        <Grid item xs={12} sm={3}>
                            <TextField
                                fullWidth
                                id="state"
                                name="state"
                                label="State"
                                placeholder="CA"
                                value={formik.values.state}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.state && Boolean(formik.errors.state)}
                                helperText={formik.touched.state && formik.errors.state}
                                disabled={isPending}
                                autoComplete="address-level1"
                            />
                        </Grid>

                        <Grid item xs={12} sm={3}>
                            <TextField
                                fullWidth
                                id="zipCode"
                                name="zipCode"
                                label="ZIP Code"
                                placeholder="12345"
                                value={formik.values.zipCode}
                                onChange={formik.handleChange}
                                onBlur={formik.handleBlur}
                                error={formik.touched.zipCode && Boolean(formik.errors.zipCode)}
                                helperText={formik.touched.zipCode && formik.errors.zipCode}
                                disabled={isPending}
                                autoComplete="postal-code"
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
                            {isPending ? 'Creating Account...' : 'Create Account'}
                        </Button>
                    </Box>

                    {/* Login Link */}
                    <Box sx={{ mt: 3, textAlign: 'center' }}>
                        <Typography variant="body2" color="text.secondary">
                            Already have an account?{' '}
                            <Button 
                                variant="text" 
                                onClick={() => navigate('/login')}
                                disabled={isPending}
                                sx={{ textTransform: 'none' }}
                            >
                                Sign in here
                            </Button>
                        </Typography>
                    </Box>
                </Box>
            </Paper>
        </Container>
    )
}