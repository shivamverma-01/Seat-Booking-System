import React, { useEffect, useState } from 'react';
import axios from 'axios';
import SeatBox from './component/SeatBox';
import UserDetailsForm from './component/UserDetailsForm';
import './App.css';

const App = () => {
    const [buses, setBuses] = useState([]);
    const [selectedBus, setSelectedBus] = useState(null);
    const [seats, setSeats] = useState([]);
    const [selectedSeats, setSelectedSeats] = useState([]);
    const [seatDetails, setSeatDetails] = useState({});
    const [isConfirmEnabled, setIsConfirmEnabled] = useState(false);

    useEffect(() => {
        const fetchBuses = async () => {
            try {
                const response = await axios.get('http://localhost:8080/api/buses');
                setBuses(response.data);
            } catch (error) {
                console.error("Error fetching buses:", error);
            }
        };
        fetchBuses();
    }, []);

    const handleBusChange = async (busNo) => {
        setSelectedBus(busNo);
        try {
            const response = await axios.get(`http://localhost:8080/api/seats/status/${busNo}`);
            console.log(response.data); // Check if seat data contains isLocked
            setSeats(response.data);
            setSelectedSeats([]);
            setSeatDetails({});
            setIsConfirmEnabled(false);
        } catch (error) {
            console.error("Error fetching seats:", error);
        }
    };
    
    

    const toggleSeatSelection = (seatNo) => {
        if (selectedSeats.includes(seatNo)) {
            setSelectedSeats(prevSelectedSeats => prevSelectedSeats.filter(s => s !== seatNo));
            delete seatDetails[seatNo];
            setSeatDetails({ ...seatDetails });
        } else if (selectedSeats.length < 5) {
            setSelectedSeats([...selectedSeats, seatNo]);
        } else {
            alert('Maximum selected seats is 5');
        }
    };

    const handleDetailChange = (seatNo, details) => {
        setSeatDetails({
            ...seatDetails,
            [seatNo]: details,
        });
    };

    useEffect(() => {
        const allDetailsFilled = selectedSeats.length > 0 && selectedSeats.length <= 5 &&
            selectedSeats.every(seatNo => seatDetails[seatNo]?.firstName && seatDetails[seatNo]?.lastName && seatDetails[seatNo]?.email);
        
        setIsConfirmEnabled(allDetailsFilled);
    }, [selectedSeats, seatDetails]);

    const handleConfirm = async () => {
        const bookingData = selectedSeats.map(seatNo => ({
            busNo: selectedBus,
            seatNo: seatNo,
            firstName: seatDetails[seatNo]?.firstName,
            lastName: seatDetails[seatNo]?.lastName,
            email: seatDetails[seatNo]?.email,
            contactNumber: seatDetails[seatNo]?.contact,
            address: seatDetails[seatNo]?.address
        }));

        try {
            const response = await axios.post('http://localhost:8080/api/bookings/confirm', bookingData);
            alert(response.status === 200 ? 'Seats confirmed successfully!' : 'There was an issue confirming your booking. Please try again.');
        } catch (error) {
            console.error('Error confirming booking:', error);
            alert('Error confirming booking. Please check the console for details.');
        }
    };

    return (
        <div className="app-container">
            <h1>Bus Seat Reservation</h1>
            <div className="bus-selection">
                <label>Select Bus:</label>
                <select onChange={(e) => handleBusChange(e.target.value)} value={selectedBus || ''}>
                    <option value="">--Select a Bus--</option>
                    {buses.map(bus => (
                        <option key={bus.id} value={bus.busNo}>{bus.busNo}</option>
                    ))}
                </select>
            </div>

            {selectedBus && (
                <div className="seats-section">
                    <h2>Select Your Seats</h2>
                    <div className="seats-container">
                        {seats.map(seat => (
                            <SeatBox
                                key={seat.seatNo}
                                seat={seat}
                                isSelected={selectedSeats.includes(seat.seatNo)}
                                onSeatClick={() => toggleSeatSelection(seat.seatNo)}
                            />
                        ))}
                    </div>
                </div>
            )}

            {selectedSeats.length > 0 && (
                <div className="details-section">
                    <h2>Passenger Details</h2>
                    {selectedSeats.map(seatNo => (
                        <UserDetailsForm
                            key={seatNo}
                            seatNo={seatNo}
                            onChange={handleDetailChange}
                            details={seatDetails[seatNo] || {}}
                        />
                    ))}
                </div>
            )}

            {selectedSeats.length > 0 && (
                <button className="confirm-button" onClick={handleConfirm} disabled={!isConfirmEnabled}>
                    Confirm Reservation
                </button>
            )}
        </div>
    );
};

export default App;
