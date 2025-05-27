import { useMutation, useQueryClient } from '@tanstack/react-query'
import axios from 'axios'
import { useFormik } from 'formik'
import React from 'react'
import * as yup from 'yup'
import {
  Box,
  Paper,
  TextField,
  Button,
  Typography,
  MenuItem,
  Container,
  CircularProgress,
  Alert
} from '@mui/material'
import { AccountBalance, Add } from '@mui/icons-material'

export default function CreateAccount({getUserProfileId}:{getUserProfileId:()=>string|undefined}) {
    const queryClient = useQueryClient()

    const validationSchema = yup.object({
        accountType: yup.string().required("Have to select Checking or Saving"),
        balance: yup.number().min(10.00, "Have deposit minimum of ten dollars")
        .required("balance is required to make an account")
    })

    const {mutate, isPending, error} : typeof useMutation = useMutation({
        mutationFn: async data => {
            const request = await axios.post(import.meta.env.VITE_API_URL + "/accounts/", data, {
                headers: {
                    "Content-Type":"application/json"
                }
            })
            return request.data
        },
        onSuccess() {
            alert("Account was created successfully")
            queryClient.invalidateQueries()
            formik.resetForm()
        },
        onError(error) {
            console.error("Account creation error:", error)
        }
    })

    const formik: typeof useFormik = useFormik({
        initialValues: {
            userProfileId: getUserProfileId(),
            accountType: "",
            balance: 10.00,
            active: true
        },
        validationSchema: validationSchema,
        onSubmit: mutate
    })

    const accountTypes = [
        { value: 'CHECKING', label: 'Checking Account' },
        { value: 'SAVING', label: 'Savings Account' }
    ]

    return (
        <Container maxWidth="sm" sx={{ py: 4 }}>
            <Paper elevation={3} sx={{ p: 4 }}>
                <Box sx={{ mb: 3, textAlign: 'center' }}>
                    <AccountBalance sx={{ fontSize: 48, color: 'primary.main', mb: 2 }} />
                    <Typography variant="h4" component="h1" gutterBottom>
                        Create New Account
                    </Typography>
                    <Typography variant="body1" color="text.secondary">
                        Open a new checking or savings account
                    </Typography>
                </Box>

                {error && (
                    <Alert severity="error" sx={{ mb: 3 }}>
                        An error occurred while creating the account: {error.message}
                    </Alert>
                )}

                <Box component="form" onSubmit={formik.handleSubmit} noValidate>
                    <Box sx={{ display: 'flex', flexDirection: 'column', gap: 3 }}>
                        
                        {/* Account Type Dropdown */}
                        <TextField
                            select
                            fullWidth
                            id="accountType"
                            name="accountType"
                            label="Account Type"
                            value={formik.values.accountType}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            error={formik.touched.accountType && Boolean(formik.errors.accountType)}
                            helperText={formik.touched.accountType && formik.errors.accountType}
                            disabled={isPending}
                        >
                            <MenuItem value="">
                                <em>Select account type</em>
                            </MenuItem>
                            {accountTypes.map((option) => (
                                <MenuItem key={option.value} value={option.value}>
                                    {option.label}
                                </MenuItem>
                            ))}
                        </TextField>

                        {/* Initial Balance Input */}
                        <TextField
                            fullWidth
                            id="balance"
                            name="balance"
                            label="Initial Deposit"
                            type="number"
                            value={formik.values.balance}
                            onChange={formik.handleChange}
                            onBlur={formik.handleBlur}
                            error={formik.touched.balance && Boolean(formik.errors.balance)}
                            helperText={formik.touched.balance && formik.errors.balance}
                            disabled={isPending}
                            InputProps={{
                                startAdornment: <span style={{ marginRight: 8 }}>$</span>,
                            }}
                            inputProps={{
                                min: 10,
                                step: 0.01
                            }}
                        />

                        {/* Submit Button */}
                        <Button
                            type="submit"
                            variant="contained"
                            size="large"
                            disabled={isPending || !formik.isValid}
                            startIcon={isPending ? <CircularProgress size={20} /> : <Add />}
                            sx={{ py: 1.5, mt: 2 }}
                        >
                            {isPending ? 'Creating Account...' : 'Create Account'}
                        </Button>
                    </Box>
                </Box>

                <Box sx={{ mt: 3, p: 2, bgcolor: 'grey.50', borderRadius: 1 }}>
                    <Typography variant="body2" color="text.secondary">
                        <strong>Note:</strong> Minimum initial deposit is $10.00. 
                        Your account will be active immediately upon creation.
                    </Typography>
                </Box>
            </Paper>
        </Container>
    )
}