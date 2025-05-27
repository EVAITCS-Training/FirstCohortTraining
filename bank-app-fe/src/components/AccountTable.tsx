// Add these imports to your existing imports
import { 
  DataGrid, 
  GridActionsCellItem
} from '@mui/x-data-grid'
import type {
    GridColDef,
  GridRowParams 
} from "@mui/x-data-grid"
import { 
  Container,
  Paper,
  Typography,
  Chip,
  IconButton,
  Tooltip,
  Box,
  Button,
  CircularProgress
} from '@mui/material'
import { 
  Edit,
  Delete,
  Visibility,
  AccountBalance,
  ErrorOutline,
  Refresh
} from '@mui/icons-material'
import { useQuery } from '@tanstack/react-query'
import axios from 'axios'

export default function AccountTable({getUserProfileId}:{getUserProfileId:() => string|undefined}) {
    const {data, isLoading, error, refetch}: typeof useQuery = useQuery({
        queryKey:['accounts'],
        queryFn: async () => {
            const response = await axios.get(import.meta.env.VITE_API_URL + "/accounts/" + getUserProfileId())
            if(response.status !== 200){
                throw new Error("Error retrieving accounts")
            }
            return response.data
        }
    })

    // Loading and error states remain the same...
    if (isLoading) {
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
                    Loading your accounts table...
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
                    Unable to load accounts table
                </Typography>
                
                <Typography variant="body1" color="text.secondary" sx={{ mb: 3 }}>
                    Please try refreshing the page
                </Typography>
                
                <Button
                    variant="contained"
                    startIcon={<Refresh />}
                    onClick={() => refetch()}
                >
                    Try Again
                </Button>
            </Box>
        )
    }

    // Define columns for the DataGrid
    const columns: GridColDef[] = [
        {
            field: 'accountNumber',
            headerName: 'Account Number',
            width: 150,
            renderCell: (params) => (
                <Typography variant="body2" sx={{ fontFamily: 'monospace' }}>
                    {params.value || 'N/A'}
                </Typography>
            )
        },
        {
            field: 'accountType',
            headerName: 'Account Type',
            width: 140,
            renderCell: (params) => (
                <Chip
                    label={params.value}
                    color={params.value === 'CHECKING' ? 'primary' : 'success'}
                    variant="outlined"
                    size="small"
                />
            )
        },
        {
            field: 'balance',
            headerName: 'Balance',
            width: 130,
            type: 'number',
            valueFormatter: (params) => {
                if (params.value == null) return '$0.00'
                return new Intl.NumberFormat('en-US', {
                    style: 'currency',
                    currency: 'USD'
                }).format(params.value)
            },
            renderCell: (params) => (
                <Typography 
                    variant="body2" 
                    sx={{ 
                        fontWeight: 'bold',
                        color: params.value < 0 ? 'error.main' : 'text.primary'
                    }}
                >
                    {new Intl.NumberFormat('en-US', {
                        style: 'currency',
                        currency: 'USD'
                    }).format(params.value || 0)}
                </Typography>
            )
        },
        {
            field: 'active',
            headerName: 'Status',
            width: 100,
            renderCell: (params) => (
                <Chip
                    label={params.value ? 'Active' : 'Inactive'}
                    color={params.value ? 'success' : 'error'}
                    variant="filled"
                    size="small"
                />
            )
        },
        {
            field: 'actions',
            type: 'actions',
            headerName: 'Actions',
            width: 120,
            getActions: (params: GridRowParams) => [
                <GridActionsCellItem
                    icon={
                        <Tooltip title="View Account">
                            <Visibility />
                        </Tooltip>
                    }
                    label="View"
                    onClick={() => handleViewAccount(params.row)}
                />,
                <GridActionsCellItem
                    icon={
                        <Tooltip title="Edit Account">
                            <Edit />
                        </Tooltip>
                    }
                    label="Edit"
                    onClick={() => handleEditAccount(params.row)}
                />,
                <GridActionsCellItem
                    icon={
                        <Tooltip title="Delete Account">
                            <Delete />
                        </Tooltip>
                    }
                    label="Delete"
                    onClick={() => handleDeleteAccount(params.row)}
                    showInMenu
                />
            ]
        }
    ]

    // Action handlers
    const handleViewAccount = (account) => {
        console.log('View account:', account)
        // Navigate to account details or open modal
    }

    const handleEditAccount = (account) => {
        console.log('Edit account:', account)
        // Open edit modal or navigate to edit page
    }

    const handleDeleteAccount = (account) => {
        console.log('Delete account:', account)
        // Show confirmation dialog
        if (window.confirm(`Are you sure you want to delete account ${account.accountNumber}?`)) {
            // Perform delete operation
        }
    }

    // Ensure data is an array
    const accounts = Array.isArray(data) ? data : []

    return (
        <Container maxWidth="lg" sx={{ py: 4 }}>
            <Paper elevation={2} sx={{ p: 3 }}>
                {/* Header */}
                <Box sx={{ mb: 3, display: 'flex', alignItems: 'center', gap: 2 }}>
                    <AccountBalance sx={{ fontSize: 32, color: 'primary.main' }} />
                    <Box>
                        <Typography variant="h4" component="h1" gutterBottom>
                            My Accounts
                        </Typography>
                        <Typography variant="body1" color="text.secondary">
                            Manage your checking and savings accounts
                        </Typography>
                    </Box>
                </Box>

                {/* Summary Stats */}
                <Box sx={{ mb: 3, display: 'flex', gap: 2 }}>
                    <Paper variant="outlined" sx={{ p: 2, flex: 1 }}>
                        <Typography variant="body2" color="text.secondary">
                            Total Accounts
                        </Typography>
                        <Typography variant="h6">
                            {accounts.length}
                        </Typography>
                    </Paper>
                    <Paper variant="outlined" sx={{ p: 2, flex: 1 }}>
                        <Typography variant="body2" color="text.secondary">
                            Total Balance
                        </Typography>
                        <Typography variant="h6" color="primary.main">
                            {new Intl.NumberFormat('en-US', {
                                style: 'currency',
                                currency: 'USD'
                            }).format(
                                accounts.reduce((sum, account) => sum + (account.balance || 0), 0)
                            )}
                        </Typography>
                    </Paper>
                </Box>

                {/* DataGrid */}
                <Box sx={{ height: 400, width: '100%' }}>
                    <DataGrid
                        rows={accounts}
                        columns={columns}
                        pageSize={5}
                        rowsPerPageOptions={[5, 10, 20]}
                        checkboxSelection
                        disableSelectionOnClick
                        loading={isLoading}
                        sx={{
                            '& .MuiDataGrid-cell': {
                                borderRight: '1px solid #f0f0f0'
                            },
                            '& .MuiDataGrid-columnHeaders': {
                                backgroundColor: '#f8f9fa',
                                fontWeight: 'bold'
                            },
                            '& .MuiDataGrid-row:hover': {
                                backgroundColor: '#f8f9fa'
                            }
                        }}
                        componentsProps={{
                            toolbar: {
                                showQuickFilter: true,
                                quickFilterProps: { debounceMs: 500 }
                            }
                        }}
                    />
                </Box>

                {/* Empty State */}
                {accounts.length === 0 && !isLoading && (
                    <Box sx={{ textAlign: 'center', py: 6 }}>
                        <AccountBalance sx={{ fontSize: 64, color: 'grey.400', mb: 2 }} />
                        <Typography variant="h6" color="text.secondary" gutterBottom>
                            No accounts found
                        </Typography>
                        <Typography variant="body2" color="text.secondary">
                            Create your first account to get started
                        </Typography>
                        <Button
                            variant="contained"
                            sx={{ mt: 2 }}
                            onClick={() => {/* Navigate to create account */}}
                        >
                            Create Account
                        </Button>
                    </Box>
                )}
            </Paper>
        </Container>
    )
}