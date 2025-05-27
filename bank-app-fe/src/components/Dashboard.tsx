import { useQuery } from '@tanstack/react-query'
import React from 'react'
import type UserProfile from '../types/UserProfile'
import axios from 'axios'
import { Box, Button, CircularProgress, Typography } from '@mui/material'
import { ErrorOutline, Refresh } from '@mui/icons-material'
import CreateAccount from './CreateAccount'
import AccountTable from './AccountTable'

export default function Dashboard() {
    const getUserProfileId = (): string | undefined => {
        try {
            const parts = sessionStorage.getItem("Authorization")?.split(".")
            if(parts?.length !== 3) {
                throw new Error("Invalid Token")
            }
            const decodedPayload = atob(parts[1])
            const payloadObject = JSON.parse(decodedPayload)
            return payloadObject.profileId as string
        } catch (error) {
            console.error(error.message)
        }
    }

    const {data, error, isLoading} = useQuery({
        queryKey: ['userProfile'],
        queryFn: async function():Promise<UserProfile> {
            const request = await axios.get(import.meta.env.VITE_API_URL + "/user-profile/" + getUserProfileId())
            const response = request
            if(response.status !== 200) {
                throw new Error("Error retriving User Profile data, please try again")
            }
            return response.data
        }
    })

    if (isLoading){
        return(
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                    bgcolor: 'background.default',
                    gap: 3
                }}
                >
                <CircularProgress 
                    size={60} 
                    thickness={4}
                    sx={{ color: 'primary.main' }}
                />
                <Typography variant="h6" color="text.secondary">
                    Loading your dashboard...
                </Typography>
            </Box>
        )
    }

    if (error) {
        return (
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    justifyContent: 'center',
                    alignItems: 'center',
                    minHeight: '100vh',
                    bgcolor: 'background.default',
                    p: 3
                }}
                >
                <ErrorOutline sx={{ fontSize: 48, color: 'error.main', mb: 2 }} />
                
                <Typography variant="h5" gutterBottom>
                    Unable to load dashboard
                </Typography>
                
                <Typography variant="body1" color="text.secondary" sx={{ mb: 3 }}>
                    Please try refreshing the page
                </Typography>
                
                <Button
                    variant="contained"
                    startIcon={<Refresh />}
                    onClick={() => window.location.reload()}
                >
                    Refresh Page
                </Button>
            </Box>
        )
    }

    return (
        <>
            <CreateAccount getUserProfileId={getUserProfileId} />
            <br />
            <AccountTable getUserProfileId={getUserProfileId} />
        </>
    )
}
