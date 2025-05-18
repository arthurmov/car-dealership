# car-dealership
YUU workshop project
### Overview

This is a console-based Java application for managing a car dealership's inventory, handling both sales and lease contracts, and persisting data using CSV files.

Users can:
- Browse and filter vehicles by various criteria
- Sell or lease a vehicle
- Store contract information persistently
- Update inventory when transactions occur

The system uses object-oriented principles such as inheritance, encapsulation, and abstraction, and demonstrates practical file I/O operations.

---

### Features

#### Inventory Management
- Load dealership and vehicle inventory from `inventory.csv`
- View all vehicles or filter by:
    - Price range
    - Year range
    - Make/Model
    - Color
    - Mileage
    - Type

#### Sales & Leasing
- Sell or lease vehicles
- Restrict leases to vehicles 3 years old or newer
- Supports:
    - Financing (for sales)
    - Lease fee and residual value (for leases)
- Automatically removes sold/leased vehicles from inventory
- Appends contracts to `contracts.csv`

#### Data Persistence
- Inventory is saved back to `inventory.csv` after updates
- Contract records are saved in `contracts.csv` in a pipe-delimited format

---

### Screenshots

---
