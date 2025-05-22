import * as yup from 'yup'
import axios from 'axios'
import { useFormik } from 'formik'
import { useMutation } from '@tanstack/react-query'
import type UserData from '../types/UserData'
import type UserProfile from '../types/UserProfile'
import { useNavigate } from 'react-router-dom'
import { Container } from '@mui/material'

export default function Register() {
    const navigate = useNavigate()
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
    const { mutate }: typeof useMutation = useMutation({
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
                return Promise.reject(new Error("Something has occured while ssaving the user credentials"))
            }

        },
        onSuccess: () => {
            alert("User succcessfully saved")
            navigate("/login")
        },
        onError: err => {
            alert("Error has occurred: " + err.message)
        }
    })
    //Hooks are special React functions that work with functional components
    //examples of these are useState, useEffect, and in this case useFormik
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
  return (
    <Container maxWidth="md">
        
    </Container>
  )
}
