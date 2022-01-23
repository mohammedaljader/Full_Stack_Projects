import {
    Avatar,
    Box,
    Button,
    Card,
    CardActions,
    CardContent,
    Divider,
    Typography
  } from '@mui/material';
  
  
  export const AccountProfile = (props) => (
    <Card>
      <CardContent>
        <Box sx={{ alignItems: 'center',  display: 'flex', flexDirection: 'column' }} >
          <Avatar
            src= '/static/images/avatars/avatar_6.png'
            sx={{
              height: 64,
              mb: 2,
              width: 64
            }}
          />
          <Typography
            color="textPrimary"
            gutterBottom
            variant="h5"
          >
            {props.user.name}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
          >
            {props.user.email}
          </Typography>
          <Typography
            color="textSecondary"
            variant="body2"
          >
            {props.user.address}
          </Typography>
        </Box>
      </CardContent>
      <Divider />
      <CardActions>
        <Button
          style={{color:"#032892"}}
          fullWidth
          variant="text"
        >
          Upload picture
        </Button>
      </CardActions>
    </Card>
  );