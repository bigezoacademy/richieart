                                    // Function to fetch and display categories in a dropdown
                                    function loadCategoriesDropdown() {
                                        // Make an AJAX request to fetch categories from the server
                                        $.ajax({
                                            url: '/categories', // Replace with your server endpoint for getting categories
                                            method: 'GET',
                                            dataType: 'json',
                                            success: function (projects) {
                                                console.log('Successfully fetched Projects:', projects);

                                                // Clear existing options
                                                $('#categorySelect').empty();

                                                // Populate the dropdown with fetched projects
                                                projects.forEach(function (project) {
                                                    console.log('show project:', project);

                                                    // Create and append option
                                                    var option = $('<option></option>')
                                                        .attr('value', project) // Set value attribute
                                                        .text(project); // Set text content

                                                    // Append option to the dropdown
                                                    $('#categorySelect').append(option);
                                                });
                                            },

                                            error: function (error) {
                                                console.error('Error fetching Categories', error);
                                            }
                                        });
                                    }

                                    // Call loadCategoriesDropdown when the 'Categories' button is clicked
                                    $('.sidenavoption[data-target-content="newproject"]').on('click', function () {
                                        console.log("New project Button clicked, starting AJAX request...");
                                        loadCategoriesDropdown();
                                        changeOption(this); // Assuming this function shows/hides the appropriate content
                                        console.log("AJAX request initiated.");
                                    });
