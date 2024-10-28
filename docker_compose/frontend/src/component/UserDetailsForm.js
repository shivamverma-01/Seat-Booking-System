import React from 'react';

const UserDetailsForm = ({ seatNo, onChange, details }) => {
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        onChange(seatNo, { ...details, [name]: value });
    };

    return (
        <div className="user-details-form">
            <h3>Seat {seatNo} Details</h3>
            <div>
                <label>First Name:</label>
                <input type="text" name="firstName" value={details.firstName || ''} onChange={handleInputChange} />
            </div>
            <div>
                <label>Last Name:</label>
                <input type="text" name="lastName" value={details.lastName || ''} onChange={handleInputChange} />
            </div>
            <div>
                <label>Email:</label>
                <input type="email" name="email" value={details.email || ''} onChange={handleInputChange} />
            </div>
            <div>
                <label>Contact:</label>
                <input type="text" name="contact" value={details.contact || ''} onChange={handleInputChange} />
            </div>
            <div>
                <label>Address:</label>
                <input type="text" name="address" value={details.address || ''} onChange={handleInputChange} />
            </div>
        </div>
    );
};

export default UserDetailsForm;
