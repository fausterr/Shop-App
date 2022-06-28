package app.service;

import org.springframework.stereotype.Service;

import app.model.Delivery;
import app.repository.DeliveryRepository;

@Service
public class DeliveryService {

	private DeliveryRepository deliveryRepository;
	
	public DeliveryService(DeliveryRepository deliveryRepository) {
		this.deliveryRepository = deliveryRepository;
	}

	public void saveDeliveryFromPost(Delivery delivery) {
		String typeDelivery = delivery.getTypeDelivery().name();
		double deliveryPrice = setDeliveryPrice(typeDelivery);
		int timeToWait = setTimeToWait(typeDelivery);
		delivery.setPrice(deliveryPrice);
		delivery.setMaxTimeInDays(timeToWait);
		save(delivery);
	}
	
	public void save(Delivery delivery) {
		deliveryRepository.save(delivery);
	}
	
	public double setDeliveryPrice(String typeDelivery) {
		double result = 0;
		switch(typeDelivery) {
		case "INPOST": 
			result = 8.5;
			break;
		case "POCZTA_POLSKA": 
			result = 7.5;
			break;	
		case "KURIER": 
			result = 10;
			break;
		default:
			result = 10;
		}
		return result;
	}
	
	public int setTimeToWait(String typeDelivery) {
		int result = 0;
		switch(typeDelivery) {
		case "INPOST":
			result = 4;
			break;
		case "POCZTA_POLSKA":
			result = 5;
			break;
		case "KURIER":
			result = 2;
			break;
		default:
			result = 5;
		}
		return result;
	}	
}
